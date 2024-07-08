Feature: E-ticaret sitesi kullanıcı işlemleri

  Scenario: Kullanıcı Girişi Yaparak Sepete Ürün Eklenmesi
    Given the user is on the login page
    When the user enters username "kadriye.sir@gmail.com" and password "123456"
    And the user clicks on the login button
    Then the user should be logged in successfully
    When the user searches for "phone"
    And the user filters the results by price range 15000 to 20000 TL
    And the user selects a random product from the filtered results
    And the user adds the product from the lowest-rated seller to the cart
    Then the product should be added to the cart successfully
