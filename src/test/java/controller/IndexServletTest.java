package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class IndexServletTest {

    private IndexServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setUp() {
        // テスト対象のサーブレットとモックオブジェクトを初期化
        servlet = new IndexServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        // モックの挙動を定義
        when(request.getContextPath()).thenReturn("");

        // サーブレットのdoGetを実行
        servlet.doGet(request, response);

        // リダイレクトが正しいパスで呼び出されたか検証
        verify(response, times(1)).sendRedirect("/ShainIndex");
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        // モックの挙動を定義
        when(request.getContextPath()).thenReturn("");

        // サーブレットのdoPostを実行
        servlet.doPost(request, response);

        // doGetが呼び出されているかを検証
        verify(response, times(1)).sendRedirect("/ShainIndex");
    }
}
