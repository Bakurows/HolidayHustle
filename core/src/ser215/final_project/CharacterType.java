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
    private LocalDate associatedHolidayDate;
    private int characterPoints;
    private Texture characterImage;
    private Sprite characterSprite;

    //Default constructor ... Default character is Frosty the snowman
    public CharacterType () {
        this.name = "Frosty";
        this.associatedHolidayDate = this.associatedHolidayDate.of(Year.now().getValue(),12,25);
        this.characterPoints = 10;
        this.characterImage = new Texture(Gdx.files.internal("Characters/Piper_Small.png"));
        this.characterSprite = new Sprite(this.characterImage);
    }

    //Single-arg constructor
    public CharacterType(String name) {
        this.name = name;
        if(this.name.equals("Frosty")) {
            this.characterImage = new Texture(Gdx.files.internal("Characters/Frosty_Small.png"));
            this.characterSprite = new Sprite(this.characterImage);
            this.associatedHolidayDate = this.associatedHolidayDate.of(Year.now().getValue(),12,25);
        }else if (this.name.equals("Jack")) {
            this.characterImage = new Texture(Gdx.files.internal("Characters/Jack_Small.png"));
            this.characterSprite = new Sprite(this.characterImage);
            this.associatedHolidayDate = this.associatedHolidayDate.of(Year.now().getValue(),10,31);
        }else if (this.name.equals("Bugs")) {
            this.characterImage = new Texture(Gdx.files.internal("Characters/Bugs_Small.png"));
            this.characterSprite = new Sprite(this.characterImage);
            this.associatedHolidayDate = this.associatedHolidayDate.of(Year.now().getValue(),4,5); //Not exact, as easter changes year to year.
        }else if (this.name.equals("Piper")) {
            this.characterImage = new Texture(Gdx.files.internal("Characters/Piper_Small.png"));
            this.characterSprite = new Sprite(this.characterImage);
            this.associatedHolidayDate = this.associatedHolidayDate.of(Year.now().getValue(),11,26); //Not exact, as easter changes year to year
        }
        //TODO Calculate character points for current year, following year, and preceding year. Set to highest value - January 1st currently gives lowest value for christmas
        this.characterPoints = (5 + (int)(3 * (5 - ((Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), this.associatedHolidayDate))) / 36.4))));
    }


    //Accessor Methods
    public String getName() {
        return name;
    }

    public int getCharacterPoints() {
        return characterPoints;
    }

    public Sprite getCharacterSprite() {
        return characterSprite;
    }
}