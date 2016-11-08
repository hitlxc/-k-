import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by xilingyuli on 2016/10/17.
 * 主窗口
 */
public class MainFrame extends JFrame {

    JTextArea result;
    JTextField path,input1,input2;
    JButton button,button2;
    JLabel label1,label2;

    //更改ui样式
    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
    }

    MainFrame()
    {
        super("基因序列K操作");

        path = new JTextField();
        path.setPreferredSize(new Dimension(150,25));
        button = new JButton("选择文件");
        button.setPreferredSize(new Dimension(120,25));
        //选择源码文件
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(path.getText());
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.showDialog(path,"确定");
                File f = chooser.getSelectedFile();
                if(f!=null) {
                    path.setText(f.getPath());
                }
            }
        });

        label1 = new JLabel("a:");
        label2 = new JLabel("b:");
        input1 = new JTextField();
        input1.setPreferredSize(new Dimension(80,25));
        input2 = new JTextField();
        input2.setPreferredSize(new Dimension(80,25));

        button2 = new JButton("确定");
        button2.setPreferredSize(new Dimension(200,25));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = getA();
                if(a<0 && a!=-1){
                    setResult("a必须大于等于零");
                    return;
                }
                int b = getB();
                if(b<0 && b!=-1){
                    setResult("b必须大于等于零");
                    return;
                }
                if (a == -1 && b == -1){
                    setResult("ab不能都为空");
                    return;
                }
                if(b<a){
                    setResult("b不能比a小");
                    return;
                }
                String fileName = getFile();
                String sequence = "";
                try {
                    String encoding="GBK";
                    File file=new File(fileName);
                    if(file.isFile() && file.exists()){ //判断文件是否存
                    InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                        BufferedReader bufferedReader = new BufferedReader(read);
                        String lineTxt = null;
                        while((lineTxt = bufferedReader.readLine()) != null){
                            sequence += (lineTxt);
                        }
                        read.close();
                    }else{
                        System.out.println("找不到指定的文件");
                    }
                } catch (Exception e2) {
                    System.out.println("读取文件内容出错");
                    e2.printStackTrace();
                }


                //sequence += String.valueOf((a+b));
                setResult(K(sequence,a,b));
               // new File(filename);
            }
        });

        result = new JTextArea();
        result.setPreferredSize(new Dimension(250,200));

        setPreferredSize(new Dimension(300,350));
        setLayout(new FlowLayout());
        add(path);
        add(button);
        add(label1);
        add(input1);
        add(label2);
        add(input2);
        add(button2);
        add(result);
        pack();

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public String getFile()
    {
        return path.getText();
    }
    public int getA()
    {
        try {
            return Integer.parseInt(input1.getText());
        }catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public int getB()
    {
        try {
            return Integer.parseInt(input2.getText());
        }catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public void setResult(String results)
    {
        result.setText(results);
    }
    public String K(String sequence,int a,int b){
        if(a == -1 && b >= 0){
            if(b>sequence.length()){
                return "";
            }
            return sequence.substring(b);
        }
        if(a >= 0 && b == -1){
            if(a>sequence.length()){
                return "";
            }
            return sequence.substring(0,a);
        }
        if(a>=0 && b>=0 && b>=a){
            if(a>sequence.length()){
                return "";
            }
            if(b>sequence.length()){
                return sequence.substring(a);
            }
            return sequence.substring(a,b);
        }
        return null;
    }

}
