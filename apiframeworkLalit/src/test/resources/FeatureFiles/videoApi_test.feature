Feature: Different operations to perform on Video List on server

Scenario Outline:1: Get the list of video present in API
    Given the user request the api to get the list of video
    Then user should get "<statusCode>" as status code
    And The lists should returns "<size>" videos    
Examples:
    |statusCode|size|
    |200       |7   |
    
Scenario Outline:2: Patch the list of video present in API
    Given the user request the api to get the list of video
    When I update video with "<ID>"from list
    Then user should get "<statusCode>" as status code
    And user should get "<statusLine>" as status line
Examples:
    |ID                      |statusCode|statusLine|
    |5b2f5b8bf69c740011a2fac8|200       |501       |  
		
Scenario Outline:3: Adding video to list
    Given The list of videos contains "<size>" videos
    When User want to add "<song>" of "<artist>" which is published on "<publishDate>"
    And I add the video to the system
    Then The video is successfully added
    And The list of videos contains "<Newsize>" videos
    
Examples:
    |size|song            |artist     |publishDate|Newsize|
    |7   |PressAssociation|LalitTewari|20/06/2018 |8      |
   
Scenario Outline:4: Deleting video from the list
    Given The list of videos contains "<size>" videos
     And I delete video at "<position>" from list
     Then The list of videos contains "<Newsize>" videos
Examples:
    |size|position|Newsize|
    |8   |7       |7      |
 
 
    
    