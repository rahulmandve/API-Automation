Feature: Running token service to test

  @QaTest
  Scenario:Get access token for service
    Given I have to build request for generate token
    When I make an API call to get token
    Then I should get success response code 200 from token service
    And I will update global access token

  @QaTest
  Scenario: Testing Courese Details service
    Given I have to make request to course details service
    When I make API call to course details service
    Then I should get Status code 200 from course details service
    And Verify below fields in response
      | Key        | value       |
      | instructor | RahulShetty |
