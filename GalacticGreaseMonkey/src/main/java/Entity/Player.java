package Entity;

import Game.GamePanel;
import Game.KeyHandler;
import Position.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        speed = 5;
        direction = "down";
        this.gp = gp;
        this.keyH = keyH;
        worldX = 100;
        worldY = 100;

        getPlayerImage();
    }

    //retrieve sprite images
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/monkeyUp1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/monkeyUp2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/monkeyDown1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/monkeyDown2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/monkeyRight1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/monkeyRight2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/monkeyLeft1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/monkeyLeft2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //update player position on key press and animation every 10ms
    public void update() {
        // Handle WASD movement

        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
            if(keyH.upPressed) {
                direction = "up";
//                int updatedY = position.getY() - position.getSpeed();
//                position.setY(updatedY);
            }

            else if(keyH.downPressed) {
                direction = "down";
//                int updatedY = position.getY() + position.getSpeed();
//                position.setY(updatedY);
            }

            else if(keyH.leftPressed) {
                direction = "left";
//                int updatedX = position.getX() - position.getSpeed();
//                position.setX(updatedX);
            }

            else if(keyH.rightPressed) {
                direction = "right";
//                int updatedX = position.getX() + position.getSpeed();
//                position.setX(updatedX);
            }

            collisionDetected = false;
            gp.collisionChecker.checkTile(this);

            if (collisionDetected == false) {
                switch (direction) {
                    case "up":
                        int updatedY = worldY - speed;
                        worldY = updatedY;
                        break;
                    case "down":
                        int updatedY1 = worldY + speed;
                        worldY = updatedY1;
                        break;
                    case "left":
                        int updatedX = worldX - speed;
                        worldX = updatedX;
                        break;
                    case "right":
                        int updatedX1 = worldX + speed;
                        worldX = updatedX1;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    //Display corresponding image based on key press
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

}
