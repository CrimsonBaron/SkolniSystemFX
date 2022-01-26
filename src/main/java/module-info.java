module cz.spsmb.skolnisystemfx2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.spsmb.skolnisystemfx2 to javafx.fxml;
    exports cz.spsmb.skolnisystemfx2;
    exports cz.spsmb.skolnisystemfx2.controllers;
    opens cz.spsmb.skolnisystemfx2.controllers to javafx.fxml;
}