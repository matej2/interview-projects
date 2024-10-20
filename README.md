# Live fotball world cup

This is an example libary to support fotball matches. Each scoreboard can have multiple games and each game can have two teams. 

## Assumptions

- app assumes that one named team can only play one game at the time
- app assumes that there should be no dupliated matches (teams with the same name)
- match class assumes that score can be set directly
- team name is specified only once
- initial team score is set automatically to 0 and cannot be overridden
- scoreboard will return ordered summary in mutable list
- when updating matches, user creates a "matcher" Match object and specifies score for each team. This matcher object should only include team names
- when finishing matches, user will create a "matcher" Match object, which will then be filtered against match list