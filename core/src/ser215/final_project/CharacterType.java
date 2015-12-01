package ser215.final_project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

/**
 * Created by Brian on 11/16/2015.
 */

public class CharacterType {
    private String name;
    private String associatedHolidayName;
    private LocalDate associatedHolidayDate;
    private int characterPoints;
    private Texture characterImage;
    private Sprite characterSprite;

    //Default constructor ... Default character is Frosty the snowman
    public CharacterType () {
        this.name = "Frosty";
        this.associatedHolidayName = "Christmas";
        this.associatedHolidayDate.of(Year.now().getValue(),12,25);
        this.characterPoints = 10;
        this.characterImage = new Texture(Gdx.files.internal("Characters/Piper_Small.png"));
        this.characterSprite = new Sprite(this.characterImage);
    }

    public CharacterType(String name, String associatedHolidayName, int year, int month, int day, int characterPoints) {
        this.name = name;
        this.associatedHolidayName = associatedHolidayName;
        this.associatedHolidayDate.of(year,month,day);
        this.characterPoints = (5 + (int)(3 * (5 - ((Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), this.associatedHolidayDate))) / 36.4))));
    }


    //Accessor Methods
    public String getName() {
        return name;
    }

    public String getAssociatedHolidayName() {
        return associatedHolidayName;
    }

    public LocalDate getAssociatedHolidayDate() {
        return associatedHolidayDate;
    }

    public int getCharacterPoints() {
        return characterPoints;
    }

    public Texture getCharacterImage() {
        return characterImage;
    }

    public Sprite getCharacterSprite() {
        return characterSprite;
    }

    //Other methods
}