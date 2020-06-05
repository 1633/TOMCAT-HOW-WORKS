package ex01.pyrmont;

import java.io.InputStream;
import java.io.IOException;

public class Request {

    private InputStream input;
    private String uri;

    public Request(InputStream input) {
        this.input = input;
    }

    /**
     * 解析HTTP请求的原始数据
     */
    public void parse() {
        // Read a set of characters from the socket
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }

        System.out.print(request.toString());
        //获取URI，相对于根路径的相对路径  比如/index.html
        uri = parseUri(request.toString());
    }

    /**
     * 解析http请求的URI
     * @param requestString 请求的内容
     * @return 截取的URI
     */
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);//截取URI串
        }
        return null;
    }

    public String getUri() {
        return uri;
    }

}