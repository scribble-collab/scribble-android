package co.scribble.android.scribble.helpers;

import com.google.android.material.textfield.TextInputLayout;

public class Valid {
    public static boolean text(TextInputLayout layout, String text) {
        if (text == null || text.length() == 0) {
            layout.setError("Cannot be empty");
            return false;
        }

        return true;
    }

    public static boolean password(TextInputLayout layout, String password) {
        if (password == null || password.length() == 0) {
            layout.setError("Cannot be empty");
            return false;
        }

        if (password.length() < 8) {
            layout.setError("Password should be 8 characters long");
            return false;
        }

        return true;
    }
}
