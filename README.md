# MoleLawn
![logo](https://github.com/user-attachments/assets/4ddce92a-3c25-4588-9579-6bfe18b84585)

A survival/adventrue game telling a story of a Mole living inside the Lawn.\
The aim was to create an enjoyable 2D digging-based game with pixel art.

It is being written in Java using libGDX UI and it's still under construction.

### Web release
There is also a JavaScript release deployed from Java with GTW compiler. You can play it [here](https://kubickipi314.github.io/MoleLawnWeb/).

### Appearance
The lawn underground map consists of 8x8 pixel tiles that are being shaded depending on the air level.
![image1573](https://github.com/user-attachments/assets/c83ba066-cfc2-47e0-b901-9a0188db56b2)


### Gameplay
We controll the mole by `arrows` and `space`.

Mole is digging tunnels buring the the ground around. It must eat worms not to die from `Hunger` as well as keeping access to the air so as not to `Suffocate`.

The `Lawn Owner` tries to kill the mole in many ways: He sticks in the `Spade`, trample the hills with `Boot` and throw `Petards` to the tunnels. For a now the main target is to survive satisfactorily long.

We can also collect `Moss` to make nest in the tunnel which will not be buried with dirt.

![MoleDeaths](https://github.com/user-attachments/assets/f705219b-f70e-4f1a-92ec-e53207688a44)

### Running
Cloned repository requires gradlew. We simply build with `./gradlew build` and run with `./gradlew run`.
