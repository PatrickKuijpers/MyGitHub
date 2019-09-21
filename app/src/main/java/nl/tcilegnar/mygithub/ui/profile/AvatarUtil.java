package nl.tcilegnar.mygithub.ui.profile;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AvatarUtil {
    private static final String TAG = "TEST";

    public void loadAndShowAvatar(@NotNull ImageView imageView, @Nullable String avatarUrl, @Nullable String fileName) {
        Context context = imageView.getContext();
        if (context == null) {
            // TODO (PK):
            return;
        }
        if (avatarUrl == null || avatarUrl.isEmpty() || fileName == null || fileName.isEmpty()) {
            showDefaultAvatar(context);
            return;
        }
        retrieveAvatar(context, avatarUrl, fileName);
        imageView.setImageBitmap(loadAvatar(context, fileName));
    }

    private void retrieveAvatar(@NonNull Context context, @NonNull String avatarUrl, @NonNull String loginName) {
        Log.i(TAG, "retrieveAvatar");
        try {
            Picasso.with(context).load(avatarUrl).into(new Target() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    Log.i(TAG, "avatar onSuccess");
                    saveAvatar(context, bitmap, loginName);
                }

                @Override
                public void onError() {
                    Log.e(TAG, "avatar onError");
                    showDefaultAvatar(context);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Exception: " + e.getMessage());
            showDefaultAvatar(context);
        }
    }

    private void saveAvatar(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String fileName) {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            Log.i(TAG, "avatar finished");
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException: " + e.getMessage());
            e.printStackTrace();
            showDefaultAvatar(context);
        }
        catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
            e.printStackTrace();
            showDefaultAvatar(context);
        }
    }

    private Bitmap loadAvatar(@NonNull Context context, @NonNull String fileName) {
        Bitmap b = null;
        try (FileInputStream fis = context.openFileInput(fileName)) {
            b = BitmapFactory.decodeStream(fis);
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
            e.printStackTrace();
        }
        return b;
    }

    private void showDefaultAvatar(@NonNull Context context) {
        Log.i(TAG, "showDefaultAvatar");
        // TODO (PK):
    }
}
