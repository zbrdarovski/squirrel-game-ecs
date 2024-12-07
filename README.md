# Stop The Squirrels ECS

## Dynamics

It's hazelnut season, use the basket to collect hazelnuts, before the squirrels snatch them.

>Caution: don't let squirrels enter the basket.

## Mechanics
- Basket manouvers
- Sneaking of squirrels
- Collecting hazelnuts
- End of game

## Assets
- [Background image](https://icons8.com/photos/photo/bark-of-an-old-tree--5a1e30588b6588000131a119) (added 09.10.2021)
- [Basket image](https://www.flaticon.com/premium-icon/picnic-basket_1135718?term=basket&page=1&position=2&page=1&position=2&related_id=1135718&origin=search) (added 09.10.2021)
- [Squirrel image](https://www.flaticon.com/free-icon/squirrel_1864534?term=squirrel&page=1&position=1&page=1&position=1&related_id=1864534&origin=search) (added 09.10.2021)
- [Hazelnut image](https://www.flaticon.com/free-icon/hazelnut_3439365?term=hazelnut&page=1&position=19&page=1&position=19&related_id=3439365&origin=tag) (added 09.10.2021)
- End result (number of hazelnuts) font (added 09.10.2021)
- [Hazelnut sound](https://freesound.org/s/344518/) (added 09.10.2021)
- [End of game sound](https://freesound.org/s/519986/) (added 09.10.2021)

>Disclaimer: the assets were available on the respective links at the day of adding them. I apologize if they are unavailable at this time.

## Project Structure:
StopTheSquirrels (structured programming):
- StopTheSquirrels.java (main class) contains the game implementation.
- DesktopLauncher.java (game window class) containts primary game window settings.

StopTheSquirrelsV2 (object oriented programming):
- StopTheSquirrelsV2.java (main class) game entry point.
- DesktopLauncherV2.java (game window class) contains primary game window settings.
- Assets.java - class that enables using, rendering and removal of game elements.
- Background.java - class used for background rendering.
- Basket.java - class for basket rendering and control.
- DynamicGameObject.java - abstract class, inherited by the basket, squirrel and hazelnut class.
- End.java - class to indicate end of game.
- GameObject.java - abstract class, from which every class (except main and launcher) inherits.
- Hazelnut.java - class for hazelnut initialization and rendering.
- Score.java - class for result update and rendering.
- Squirrel.java - class for squirrel initialization and rendering.

## Useful Links
- [Free Image Resizing](https://resizeimage.net/)
- [Free File Converter](https://www.freeconvert.com/)

## Development Phases
![screenshot](images/draft.png?raw=true "Game draft.")

Image 1: Game draft.

![previewSP](images/previewSP.PNG?raw=true "Preview of the structured programming implementation.")

Image 2: Preview of the structured programming implementation.

![previewOOP](images/previewOOP.PNG?raw=true "Preview of the object oriented programming implementation.")

Image 3: Preview of the object oriented programming implementation.
