package controller;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swe_2020_next.LoginActivity;
import com.example.swe_2020_next.MainActivity;
import com.example.swe_2020_next.ProfileFragment;
import com.example.swe_2020_next.R;

import model.Dater;
import model.User;

public class EditProfileController extends AppCompatActivity {
    private Dater dater;
    private User user;
    private Activity activity;

    public EditProfileController(Activity activity) {
        dater = Dater.getInstance();
        user = dater.getCurrentUser();
        this.activity = activity;
    }

    public void saveProfileChanges(String newFirstName, String newLastName, String newAge, String newGender, String newLanguage, String newInfoText, String newHobby, String newEmail, boolean newVisibility) {
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setAge(Integer.parseInt(newAge));
        user.setGender(newGender);
        user.setLanguage(newLanguage);
        user.setInfoText(newInfoText);
        user.addHobby(newHobby);
        user.setEmail(newEmail);
        user.setVisible(newVisibility);

        //TODO: Wechsle zurück auf das Profile Fragment
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        /*getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ProfileFragment(activity)).commit();*/

    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getAge() {
        return Integer.toString(user.getAge());
    }

    public String getGender() {
        return user.getGender();
    }

    public String getLanguage() {
        return user.getLanguage();
    }

    public String getInfoText() {
        return user.getInfoText();
    }

    //Hier wird das zuletzt hinzugefügte Hobby zurückgegeben. Muss geändert werden, jenachdem wie wir Hobbies Anzeigen/Ändern/Hinzufügen wollen.
    public String getHobby() {
        return user.getHobbies().getLast().getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public boolean getVisibility() {
        return user.isVisible();
    }
}
