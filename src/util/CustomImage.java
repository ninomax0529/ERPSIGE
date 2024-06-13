/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Departamento IT
 */
import javafx.scene.image.ImageView;

    public class CustomImage {

        private ImageView image;
        private String string;

        CustomImage(ImageView img, String string) {
            this.image = img;
            this.string = string;
        }

        public void setImage(ImageView value) {
            image = value;
        }

        public ImageView getImage() {
            return image;
        }

        public void setSring(String string) {
            this.string = string;
        }

        public String getString() {
            return this.string;
        }
    }

