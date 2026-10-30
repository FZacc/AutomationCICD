
    @Tag
    Feature: Purchase product from E-commerce website

        Background:
            Given I landed on E-commerce site
        @Regression
        Scenario Outline: Positive test of submitting the order
            Given I logged in by using <email> and <password>
            When add <productName> to cart
            And checkout <productName> and submit the order
            Then "Thankyou for the order." is displayed on confirmation page

            Examples:
                | email              | password       | productName |  |
                | mfzahraa@gmail.com | Little_Sword@1 | ZARA COAT 3 |  |
