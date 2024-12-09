@swap
Feature: Cryptocurrency Swap Feature
  Background:
    Given User logs in his/her account

  Scenario: Successful swap of USDT to BTC
    And User has 500 USDT balance
    When User navigates to the Swap page
    And User selects "USDT" as the source currency
    And User selects "BTC" as the target currency
    And User enters "100" as the amount
    And User confirms the swap
    Then The swap is executed successfully
    And User sees that his/her BTC balance is updated
    And User sees that his/her USDT balance is deducted
    And The transaction is recorded in the Swap history

  Scenario: Swap fails due to insufficient balance
    And User has 10 USDT balance
    When User attempts to swap "100 USDT" to "BTC"
    Then User gets an error message stating "Insufficient balance"
    And User sees no changes are made to his/her balances

  Scenario: Real-time rates are updated correctly
    And User navigates to the Swap page
    When User selects "USDT" as the source currency
    And User selects "BTC" as the target currency
    Then User sees that the displayed exchange rate is updated every 5 seconds

  Scenario: Minimum swap amount restriction
    And User navigates to the Swap page
    When User attempts to swap an amount "3"
    Then User gets an error message stating "3 is below the minimum limit-5"
    And User sees that no changes are made to his/her balances

  Scenario: Maximum swap amount restriction
    And User navigates to the Swap page
    When User attempts to swap an amount 550
    Then User gets an error message stating "Amount exceeds the maximum limit-500"
    And User sees that no changes are made to his/her balances



