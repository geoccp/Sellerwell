package org.jeecg.modules.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/selenium/productor")
public class ProductorController {

    @GetMapping(value = "/productSearch")
    public void productSearch() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        //解决DevToolsActivePort文件不存在的报错
        options.addArguments("--no-sandbox");
        //谷歌文档提到需要加上这个属性来规避bug
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        //浏览器不提供可视化界面。Linux下如果系统不支持可视化不加这条会启动失败
        options.addArguments("--headless");
    }
}
