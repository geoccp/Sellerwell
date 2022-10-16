package org.jeecg.modules.selenium;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.tool.entity.ToolGeeProductSearch;
import org.jeecg.modules.tool.service.IToolGeeProductSearchService;
import org.jeecg.modules.tool.toolGeekseeker.entity.ToolGeekseeker;
import org.jeecg.modules.tool.toolGeekseeker.service.IToolGeekseekerService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/selenium/sellerwell")
public class SellerwellController {

    @Autowired
    private IToolGeeProductSearchService toolGeeProductSearchService;
    @Autowired
    private IToolGeekseekerService toolGeekseekerService;

    private WebDriver driver;
    private Wait<WebDriver> wait;
    private String geekUrl = "http://geek.sellerwell.com";

    @GetMapping(value = "/productSearch")
    public String productSearch() throws InterruptedException {
        toolGeeProductSearchService.deleteTable();
        ChromeOptions options = new ChromeOptions();
        //解决DevToolsActivePort文件不存在的报错
        options.addArguments("--no-sandbox");
        //谷歌文档提到需要加上这个属性来规避bug
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        //浏览器不提供可视化界面。Linux下如果系统不支持可视化不加这条会启动失败
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.get("http://geek.sellerwell.com/geekseeker/#/productAnalysis/productSearch");
        wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(By.xpath("//div[@class='ant-tabs-nav-list']/button[2]"))).click();
        wait.until(d -> d.findElement(By.id("userLogin_username"))).sendKeys("17720491608");
        wait.until(d -> d.findElement(By.id("userLogin_password"))).sendKeys("Eccang123");
        wait.until(d -> d.findElement(By.xpath("//button[@class='ant-btn ant-btn-primary ant-btn-two-chinese-chars']"))).click();
        Thread.sleep(1000l);
        //产品分析
        wait.until(d -> d.findElement(By.xpath("//div[@class='MenuBox']/ul/li[4]"))).click();
        Thread.sleep(1000l);
        //产品搜索
        wait.until(d -> d.findElement(By.xpath("//ul[@id='productAnalysis$Menu']/li[1]/div"))).click();
        //添加查询条件
        wait.until(d -> d.findElement(By.xpath("//div[@id='geekseeker-root']/div/div[3]/div/div/div[3]/div/div/div[2]/div[5]/div[3]/div[1]/div[5]"))).click();
        wait.until(d -> d.findElement(By.xpath("//div[@id='geekseeker-root']/div/div[3]/div/div/div[3]/div/div/div[2]/div[6]/div[3]/div[1]/div[3]"))).click();
        wait.until(d -> d.findElement(By.xpath("//div[@id='geekseeker-root']/div/div[3]/div/div/div[3]/div/div/div[2]/div[8]/div[3]/div[1]/div[4]"))).click();
        wait.until(d -> d.findElement(By.xpath("//div[@id='geekseeker-root']/div/div[3]/div/div/div[3]/div/div/div[2]/div[11]/div[3]/div[1]/div[3]"))).click();
        //点击查询
        wait.until(d -> d.findElement(By.xpath("//div[@id='geekseeker-root']/div/div[3]/div/div/div[3]/div/div/div[3]/div[2]/button[1]"))).click();
        // 切换到100条一页
        wait.until(d -> d.findElement(By.xpath("//div[@class='ant-select ant-pagination-options-size-changer ant-select-single ant-select-show-arrow']"))).click();
        Thread.sleep(1000l);
        wait.until(d -> d.findElement(By.xpath("//div[@class='rc-virtual-list-holder-inner']/div[4]"))).click();
        Thread.sleep(1000l);
        List<WebElement> pageLiList = new ArrayList<>();
        //如果下一页为灰色，则跳出循环
        Integer pageSum = 0;
        do {
            log.info(String.format("第%s页", pageSum++));
            pageLiList = driver.findElements(By.xpath("//ul[@class='ant-pagination']/li"));
            wait.until(d -> d.findElement(By.xpath("//tbody[@class='ant-table-tbody']/tr[2]")));
            Thread.sleep(1000l);
            List<WebElement> productList = driver.findElements(By.xpath("//tbody[@class='ant-table-tbody']/tr"));
            //去掉一个空行
            productList.remove(0);
            List<ToolGeeProductSearch> list = new ArrayList<>();
            Integer indexIn = 0;
            for (WebElement w : productList) {
                log.info(String.format("第%s条记录", indexIn++));
                ToolGeeProductSearch geeProductSearch = new ToolGeeProductSearch();
                geeProductSearch.setClassification(getAttribute(wait, indexIn, "", "data-row-key"));
                geeProductSearch.setProductId(getAttribute(wait, indexIn, "", "data-row-key"));
                geeProductSearch.setImgIndex(getAttribute(wait, indexIn, "//td[3]/div/div/img", "src"));
                geeProductSearch.setTitle(getText(wait, indexIn, "//td[4]/div/div[1]"));
                geeProductSearch.setPath(getText(wait, indexIn, "//td[5]/div/div[1]"));
                geeProductSearch.setPathTzh(getText(wait, indexIn, "//td[5]/div/div[2]"));
                String[] paths = geeProductSearch.getPathTzh().split("->");
                if (paths.length > 0)
                    geeProductSearch.setPath1(paths[0]);
                if (paths.length > 1)
                    geeProductSearch.setPath2(paths[1]);
                if (paths.length > 2)
                    geeProductSearch.setPath3(paths[2]);
                if (paths.length > 3)
                    geeProductSearch.setPath4(paths[3]);
                if (paths.length > 4)
                    geeProductSearch.setPath5(paths[4]);

                geeProductSearch.setPrice(getText(wait, indexIn, "//td[6]/div"));
                geeProductSearch.setStrRank(getText(wait, indexIn, "//td[7]/div"));
                geeProductSearch.setComment(getText(wait, indexIn, "//td[8]/div"));
                geeProductSearch.setGoodCommentRate(getText(wait, indexIn, "//td[9]/div"));
                geeProductSearch.setBadCommentRate(getText(wait, indexIn, "//td[10]/div"));
                geeProductSearch.setStar(getText(wait, indexIn, "//td[11]/div"));
                geeProductSearch.setDiscount(getText(wait, indexIn, "//td[12]/div"));
                geeProductSearch.setDistribution(getText(wait, indexIn, "//td[13]/div"));
                geeProductSearch.setAskAndQ(getText(wait, indexIn, "//td[14]/div"));
                geeProductSearch.setRankType(getText(wait, indexIn, "//td[15]/div"));
                geeProductSearch.setShelfTime(getText(wait, indexIn, "//td[16]/div"));
                geeProductSearch.setSellerCount(getText(wait, indexIn, "//td[17]/div"));
                list.add(geeProductSearch);
            }
            toolGeeProductSearchService.saveBatch(list);
            //执行下一页
            if (pageLiList.get(pageLiList.size() - 2).getAttribute("aria-disabled").equals("false")) {
                pageLiList.get(pageLiList.size() - 2).click();
                Thread.sleep(1000l);
            }
        } while (pageLiList.get(pageLiList.size() - 2).getAttribute("aria-disabled").equals("false"));

        return driver.getTitle();
    }


    @GetMapping(value = "/hotIndustry")
    public void hotIndustry() throws InterruptedException {
        //        清空数据表
        Date startDate = new Date();
        toolGeekseekerService.deleteTable();
        initDriver();
        loginSellerwell();
        openHotIndustry(1);
        List<WebElement> pageLiList = new ArrayList<>();
        //如果下一页为灰色，则跳出循环
        Integer pageSum = 1;
        do {
            if (pageSum / 5 * 5 == pageSum) {
                driver.quit();
                Thread.sleep(4000l);
                initDriver();
                loginSellerwell();
                openHotIndustry(pageSum);
                pageLiList = driver.findElements(By.xpath("//ul[@class='ant-pagination']/li"));
            } else {
                pageLiList = driver.findElements(By.xpath("//ul[@class='ant-pagination']/li"));
            }
            log.info(String.format("第%s页", pageSum++));
            wait.until(d -> d.findElement(By.xpath("//tbody[@class='ant-table-tbody']/tr[2]")));
            Thread.sleep(1000l);
            List<WebElement> productList = driver.findElements(By.xpath("//tbody[@class='ant-table-tbody']/tr"));
            //去掉一个空行
            productList.remove(0);
            List<ToolGeekseeker> list = new ArrayList<>();
            Integer indexIn = 1;

            for (WebElement w : productList) {
                log.info(String.format("第%s条记录", indexIn));
                ToolGeekseeker toolGeekseeker = new ToolGeekseeker();
                toolGeekseeker.setClassification(getAttribute(wait, indexIn, "", "data-row-key"));
                toolGeekseeker.setIndustryName(getText(wait, indexIn, "//td[2]"));
                toolGeekseeker.setPath(getText(wait, indexIn, "//td[3]/div/div[1]"));
                toolGeekseeker.setPathZh(getText(wait, indexIn, "//td[3]/div/div[2]"));
                String[] paths = toolGeekseeker.getPathZh().split("->");
                if (paths.length > 0)
                    toolGeekseeker.setPath1(paths[0]);
                if (paths.length > 1)
                    toolGeekseeker.setPath2(paths[1]);
                if (paths.length > 2)
                    toolGeekseeker.setPath3(paths[2]);
                if (paths.length > 3)
                    toolGeekseeker.setPath4(paths[3]);
                if (paths.length > 4)
                    toolGeekseeker.setPath5(paths[4]);
                String textValue = getText(wait, indexIn, "//td[4]");
                if (StringUtils.isNotEmpty(textValue))
                    toolGeekseeker.setHotSalesIndex(Double.parseDouble(
                            textValue.replace(",", "").replace("%", "")));
                textValue = getText(wait, indexIn, "//td[5]");
                if (StringUtils.isNotEmpty(textValue))
                    toolGeekseeker.setProductTotal(Double.parseDouble(
                            textValue.replace(",", "").replace("%", "")));
                textValue = getText(wait, indexIn, "//td[6]");
                if (StringUtils.isNotEmpty(textValue))
                    toolGeekseeker.setProductSales(Double.parseDouble(
                            textValue.replace(",", "").replace("%", "")));
                textValue = getText(wait, indexIn, "//td[7]");
                if (StringUtils.isNotEmpty(textValue))
                    toolGeekseeker.setIndustrySaleRate(Double.parseDouble(
                            textValue.replace(",", "").replace("%", "")) / 100);
                textValue = getText(wait, indexIn, "//td[8]");
                if (StringUtils.isNotEmpty(textValue))
                    toolGeekseeker.setPriceAvg(Double.parseDouble(
                            textValue.replace(",", "").replace("%", "")));
                textValue = getText(wait, indexIn, "//td[9]");
                if (StringUtils.isNotEmpty(textValue))
                    toolGeekseeker.setTopCommentTotal(Double.parseDouble(
                            textValue.replace(",", "").replace("%", "")));
                if (toolGeekseeker.getTopCommentTotal() != null &&
                        toolGeekseeker.getProductTotal() != null && toolGeekseeker.getProductTotal() > 0)
                    toolGeekseeker.setOrderIndex(toolGeekseeker.getTopCommentTotal() / toolGeekseeker.getProductTotal());
                indexIn++;
                list.add(toolGeekseeker);
            }
            toolGeekseekerService.saveBatch(list);
            //执行下一页
            if (pageLiList.get(pageLiList.size() - 2).getAttribute("aria-disabled").equals("false")) {
                pageLiList.get(pageLiList.size() - 2).click();
                Runtime.getRuntime().gc();
                Thread.sleep(1000l);
            }
        } while (pageLiList.get(pageLiList.size() - 2).getAttribute("aria-disabled").equals("false"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        driver.quit();
        log.info(String.format("数据爬取完工,开始时间：%s-%s", df.format(startDate), df.format(new Date())));
    }

    /**
     * 初始化Driver
     */
    private void initDriver() {
        ChromeOptions options = new ChromeOptions();
        //解决DevToolsActivePort文件不存在的报错
        options.addArguments("--no-sandbox");
        //谷歌文档提到需要加上这个属性来规避bug
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        //浏览器不提供可视化界面。Linux下如果系统不支持可视化不加这条会启动失败
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    /**
     * 模拟用户登录
     */
    private void loginSellerwell() throws InterruptedException {
        driver.get(geekUrl);
        wait = new WebDriverWait(driver, 10);
        wait.until(d -> d.findElement(By.xpath("//div[@class='ant-tabs-nav-list']/button[2]"))).click();
        wait.until(d -> d.findElement(By.id("userLogin_username"))).sendKeys("17720491608");
        wait.until(d -> d.findElement(By.id("userLogin_password"))).sendKeys("Eccang123");
        wait.until(d -> d.findElement(By.xpath("//button[@class='ant-btn ant-btn-primary ant-btn-two-chinese-chars']"))).click();
        Thread.sleep(1000l);
    }

    /**
     * 打开热销行业页面指定页码
     */
    private void openHotIndustry(Integer page) throws InterruptedException {
        String currentWindow = driver.getWindowHandle();    //获取当前窗口句柄
        //热销行业
        wait.until(d -> d.findElement(By.xpath("//div[@id='geekseeker-root']/div/div[2]/div/div[2]/div[2]/div/div[5]"))).click();
        Thread.sleep(1000l);
        Set<String> handles = driver.getWindowHandles();    //获取最新窗口句柄
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!currentWindow.equals(next)) {
                driver.switchTo().window(next);
                break;
            }
        }
        Thread.sleep(1000l);
        // 切换到100条一页
        wait.until(d -> d.findElement(By.xpath("//div[@class='ant-select ant-pagination-options-size-changer ant-select-single ant-select-show-arrow']"))).click();
        Thread.sleep(1000l);
        wait.until(d -> d.findElement(By.xpath("//div[@class='rc-virtual-list-holder-inner']/div[4]"))).click();
        Thread.sleep(1000l);
        WebElement webElement = wait.until(d -> d.findElement(By.xpath("//div[@class='ant-pagination-options-quick" +
                "-jumper" +
                "']/input")));
        webElement.sendKeys(page.toString());
        webElement.sendKeys(Keys.ENTER);
    }


    /**
     * 循环两次，报错后返回空值
     *
     * @param w
     * @return
     * @throws InterruptedException
     */
    private String getTextByW(WebElement w, String xPath) throws InterruptedException {
        int i = 0;
        while (i < 1) {
            try {
                i++;
                return w.findElement(By.xpath(xPath)).getText();
            } catch (Exception e) {
                log.info(String.format("第%s次异常", i));
                Thread.sleep(1000l);
                continue;
            }
        }
        return "";
    }

    private String getAttributeByW(WebElement w, String xPath, String attriName) throws InterruptedException {
        int i = 0;
        while (i < 1) {
            try {
                i++;
                if (xPath.equals(""))
                    return w.getAttribute(attriName);
                else
                    return w.findElement(By.xpath(xPath)).getAttribute(attriName);
            } catch (Exception e) {
                log.info(String.format("第%s次异常", i));
                Thread.sleep(1000l);
                continue;
            }
        }
        return "";
    }


    private String getText(Wait<WebDriver> wait, Integer index, String sxPath) throws InterruptedException {
        int i = 0;
        sxPath = String.format("//tbody[@class='ant-table-tbody']/tr[%s]", index + 1).concat(sxPath.replace("//", "/"));
        while (i < 2) {
            try {
                i++;
                String finalSxPath = sxPath;
                return wait.until(d -> d.findElement(By.xpath(finalSxPath))).getText();
            } catch (Exception e) {
                log.info(String.format("第%s次异常", i));
                Thread.sleep(1000l);
                continue;
            }
        }
        return "";
    }

    private String getAttribute(Wait<WebDriver> wait, Integer index, String sxPath, String attriName) throws InterruptedException {
        int i = 0;
        sxPath = String.format("//tbody[@class='ant-table-tbody']/tr[%s]", index + 1).concat(sxPath.replace("//", "/"));

        while (i < 2) {
            try {
                i++;
                String finalSxPath = sxPath;
                return wait.until(d -> d.findElement(By.xpath(finalSxPath))).getAttribute(attriName);
            } catch (Exception e) {
                log.info(String.format("第%s次异常", i));
                Thread.sleep(1000l);
                continue;
            }
        }
        return "";
    }


}
