
    @Tag
    Feature: Error Validation in E-commerce website

        @ErrorValidation
        Scenario Outline: Positive test of submitting the order
            Given I landed on E-commerce site
            When I logged in by using <email> and <password>
            Then "Incorrect email or password." is displayed on login page

            Examples:
                | email              | password      | productName |  |
                | mfzahraa@gmail.com | Little_Sword@ | ZARA COAT 3 |  |
