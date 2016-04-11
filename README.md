# QRCode
### 使用zxing的libs创建二维码的小Demo

#### 解析二维码:

`startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);`

`    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            tvResult.setText(result);
        }
    }`    
#### 生成二维码
`String input = etText.getText().toString();
        if (input.equals("")) {
            Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //Bitmap bitmap = EncodingUtils.createQRCode(input, 1000, 1000, null);  // (二维码内容, 二维码宽度, 二维码高度, 二维码中间LOGO)
            Bitmap bitmap = EncodingUtils.createQRCode(
                    input, 1000, 1000, logo.isChecked() ?
                            BitmapFactory.decodeResource(getResources(),
                                    R.mipmap.ic_launcher) : null);  // (二维码内容, 二维码宽度, 二维码高度, 二维码中间LOGO)
            ivResult.setImageBitmap(bitmap);
        }`    
## libzxing用法：
1. 将libzxing文件夹手动拖入想要使用二维码的项目根目录下  
ps:不要new-->new Module-->Android Library;也不要new-->new Module-->java Library.

2. 如果出现23.0.0等SDK类的错误，请将Android文件列表切换成Project文件列表，在libzxing文件夹下
   找到build.gradle文件，将[buildToolsVersion "23.0.0"] 改成 [buildToolsVersion "23.0.3"] ，
   然后再菜单栏选Tools--->Android--->Sync Project with gradle files.

3. 将libzxing文件夹像[.jar]文件一样导入Android Studio依赖.操作方法如下:
   Project Structure ---> 选择app，然后点击右面Dependencies，再点击“+”,选择Module dependency,  
   点击OK ---> OK ; 让系统自动ReBuild  Project 
   
 #### 欢迎讨论 ： <10390342@qq.com>
