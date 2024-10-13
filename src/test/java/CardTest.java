import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    // исправленный код

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        System.setProperty("selenide.browser", "Chrome");
        System.setProperty("selenide.headless", "true");
        System.setProperty("selenide.browserSize", "1920x1080");
        open("http://localhost:9999");
    }

    @Test
    void shouldTest() {
        $("[data-test-id=name] input").setValue("Гриша Иванов");
        $("[data-test-id=phone] input").setValue("+79158789966");
        $("[data-test-id=agreement]").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestNameEnglishLetters() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Pavel");

        $("[data-test-id=phone] input").setValue("+79158789966");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldTestNameNumber() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Павел Иванов966");

        $("[data-test-id=phone] input").setValue("+79158789966");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldTestNameSymbols() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Павел Иванов%");

        $("[data-test-id=phone] input").setValue("+79158789966");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldNameTestEmptyLine() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("");

        $("[data-test-id=phone] input").setValue("+79158789966");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldTestPhoneSymbols() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Павел Пупкин");

        $("[data-test-id=phone] input").setValue("++79158789966");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldTestPhoneMoreNumbers() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иван Иванов");

        $("[data-test-id=phone] input").setValue("+791587899666");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldTestPhoneLessNumbers() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Павел Пупкин");

        $("[data-test-id=phone] input").setValue("+791587899666");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79158789966."));

    }

    @Test
    void shouldTestPhoneSpace() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Пашка Пупкин");

        $("[data-test-id=phone] input").setValue("+7915 89966");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79158789966."));

    }

    @Test
    void shouldTestPhoneNumberInsteadPlus() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Павел Громов");

        $("[data-test-id=phone] input").setValue("79158789966");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79158789966."));

    }

    @Test
    void shouldTestPhoneEmptyLine() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Павел Громов");

        $("[data-test-id=phone] input").setValue("");

        $("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldTestCheckBox() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Павел Громов");

        $("[data-test-id=phone] input").setValue("+79158789966");

        //$("[data-test-id=agreement]").click();

        $(".button__content").click();

        $("[data-test-id=agreement].input_invalid .checkbox__box").should(exist);

    }
}