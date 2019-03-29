# K? BEMC^2 Brainstorm
### Robot Parts
* Drive Train (Left and Right 40:1 DcMotor)
* Collection (40:1 DcMotor)
* Lift (40:1 DcMotor)
* Preloaded Auto Ball Release (Servo)
* Camera (Logitech WebCam)
### Robot Functions
* Turn on Collection Mechanism
* Release from Collection Mechanism
* Lift Upward
* Lift Downward
* Release Preloaded Auto Ball
* Drive Forward/Backward
* Drive Turn Right/Left
* Get List of Computer Vision Recognitions
* Get Camera Frame
### Auto Path
1. Drive Off the Ramp
2. Knock Balls off Center Game Piece
3. Determine the Right Basket to Score In
4. Score Ball in Correct Basket
5. Search for Balls
    1. Drive to Side of Field
    2. Rotate Until a Ball is Found by Camera
    3. Move to New Location if Nothing is Found
6. Line Up Robot with Ball
7. Drive Forward with Collection On
8. Drive Back to Basket
9. Lift Upwards
10. Repeat Steps 4-9
11. Park on Ramp