package com.liu.tomcat;

import com.liu.tomcat.util.Constants;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 把一个socket的OutputStream封装成一个Response对应。
 * @author Liuweian
 */
public class Response implements HttpServletResponse {

    private OutputStream outputStream;

    private Request request;

    private PrintWriter writer;


    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * 向OutputStream对象中写入数据，
     */
    public void sendStaticResource() throws IOException {
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        PrintWriter writer = null;

        try {
            if (request.getUri() != null) {
                File file = new File(Constants.DEFAULT_WEB_ROOT, request.getUri());
                writer = new PrintWriter(outputStream, true);
                if (file.exists()) {
                    fis = new FileInputStream(file);
                    int ch = fis.read(buffer, 0, 1024);

                    if (!request.getUri().contains(".json")) {
                        String html = "http/1.1 200 ok\n" +"\n\n";
                        writer.println(html);
                    }

                    while (ch != -1) {
                        String readResult = new String(buffer, 0, ch, "UTF-8");
                        writer.println(readResult);
                        ch = fis.read(buffer, 0, 1024);
                    }
                } else {
                    String errorMsg = "You request file not find, Please check it.";
                    writer.write(errorMsg);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (writer != null) {
                writer.close();
            }
        }


    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        // boolean是否开始autoFlush。
        //      如果为true，表示对println()方法的任何调用都会刷新输出，但是调用print()方法不会输出。
        writer = new PrintWriter(outputStream, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String charset) {

    }

    @Override
    public void setContentLength(int len) {

    }

    @Override
    public void setContentLengthLong(long len) {

    }

    @Override
    public void setContentType(String type) {

    }

    @Override
    public void setBufferSize(int size) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale loc) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String name) {
        return false;
    }

    @Override
    public String encodeURL(String url) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String url) {
        return null;
    }

    @Override
    public String encodeUrl(String url) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String url) {
        return null;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {

    }

    @Override
    public void sendError(int sc) throws IOException {

    }

    @Override
    public void sendRedirect(String location) throws IOException {

    }

    @Override
    public void setDateHeader(String name, long date) {

    }

    @Override
    public void addDateHeader(String name, long date) {

    }

    @Override
    public void setHeader(String name, String value) {

    }

    @Override
    public void addHeader(String name, String value) {

    }

    @Override
    public void setIntHeader(String name, int value) {

    }

    @Override
    public void addIntHeader(String name, int value) {

    }

    @Override
    public void setStatus(int sc) {

    }

    @Override
    public void setStatus(int sc, String sm) {

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getHeader(String name) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }

    @Override
    public void setTrailerFields(Supplier<Map<String, String>> supplier) {

    }

    @Override
    public Supplier<Map<String, String>> getTrailerFields() {
        return null;
    }
}
