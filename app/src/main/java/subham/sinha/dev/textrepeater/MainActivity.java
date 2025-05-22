package subham.sinha.dev.textrepeater;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {
Button repeat,           reset;
TextView result;
EditText ed1,ed2;
CheckBox space,index,line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar();

        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.input);
        ed2=findViewById(R.id.input_r);
        repeat=findViewById(R.id.button);
        reset=findViewById(R.id.reset);
        result=findViewById(R.id.result);
        space=findViewById(R.id._space);
        index=findViewById(R.id._index);
        line=findViewById(R.id._line);

reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        result.setText("Repeated Text");
        ed2.setText("");
        ed1.setText("");
    }
});
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              try {
                  String text = ed1.getText().toString().trim();
                  String countText = ed2.getText().toString().trim();

                  if (text.isEmpty()) {

                      ed1.setError("Input Field Is Empty");
                      return;
                  }
                  if (countText.isEmpty()) {
                      ed2.setError("Input Field Is Empty");
                      return;
                  }

                  int n = Integer.parseInt(countText);
                  StringBuilder sb = new StringBuilder();

                  for (int i = 1; i <= n; i++) {
                      if (index.isChecked()) {
                          sb.append(i).append(".");
                      }
                      sb.append(text);
                      if (space.isChecked()) {
                          sb.append(" ");
                      }
                      if (line.isChecked()) {
                          sb.append("\n");
                      }
                  }

                  result.setText(sb.toString());
              } catch (Exception e) {
                  throw new RuntimeException(e);
              }
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
              cm.setText(result.getText());
                Toast.makeText(getApplicationContext(),"Copied To ClipBoard",Toast.LENGTH_SHORT).show();
//                cm.setPrimaryClip(result);

            }
        });


        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
     public void about(View v){
         new MaterialAlertDialogBuilder(MainActivity.this)
                 .setTitle("About the Developer")
                 .setMessage("Text Repeater is a simple yet powerful tool that lets you repeat any text multiple times with customizable formatting. Developed with ❤️ By Subham Sinha.")
                 .setPositiveButton("OK", null)
                 .show();
     }
public void github(View v){
        Intent i=new Intent().setAction(Intent.ACTION_VIEW).setData(Uri.parse("https://github.com/subhamsinhadev/Text-Repeater-Android"));
        startActivity(i);
}
}