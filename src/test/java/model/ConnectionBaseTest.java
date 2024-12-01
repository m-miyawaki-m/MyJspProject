package model;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.MockedStatic;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionBaseTest {

    @Test
    public void testGetConnection() throws NamingException, SQLException {
        // モック作成
        Context mockContext = mock(Context.class);
        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);

        // 振る舞いを定義
        when(mockContext.lookup("java:comp/env/jdbc/myproject")).thenReturn(mockDataSource);
        when(mockDataSource.getConnection()).thenReturn(mockConnection);

        // InitialContextをモック化
        try (var mockedStatic = mockStatic(InitialContext.class)) {
            mockedStatic.when(InitialContext::new).thenReturn((InitialContext) mockContext);

            // メソッドの呼び出し
            Connection connection = ConnectionBase.getConnection();
            assertNotNull("Connection should not be null", connection);

            // 動作確認
            verify(mockContext).lookup("java:comp/env/jdbc/myproject");
            verify(mockDataSource).getConnection();
        }
    }

    @Test(expected = NamingException.class)
    public void testGetConnectionThrowsNamingException() throws NamingException, SQLException {
        // モックを作成
        Context mockContext = mock(Context.class);

        // モックの振る舞いを設定
        when(mockContext.lookup("java:comp/env/jdbc/myproject")).thenThrow(new NamingException("Naming Exception"));

        // InitialContextをモックする
        try (MockedStatic<InitialContext> mockedInitialContext = mockStatic(InitialContext.class)) {
            mockedInitialContext.when(InitialContext::new).thenReturn((InitialContext) mockContext);

            // メソッドをテスト（例外がスローされることを期待）
            ConnectionBase.getConnection();
        }
    }

    @Test(expected = SQLException.class)
    public void testGetConnectionThrowsSQLException() throws NamingException, SQLException {
        // モックの作成
        Context mockContext = mock(Context.class);  // Contextをモック
        DataSource mockDataSource = mock(DataSource.class);  // DataSourceをモック

        // モックの振る舞いを設定
        when(mockContext.lookup("java:comp/env/jdbc/myproject")).thenReturn(mockDataSource);
        when(mockDataSource.getConnection()).thenThrow(new SQLException("SQL Exception"));

        // InitialContextをモック
        try (MockedStatic<InitialContext> mockedStatic = mockStatic(InitialContext.class)) {
            mockedStatic.when(InitialContext::new).thenReturn((InitialContext) mockContext);

            // テスト対象メソッドを実行
            ConnectionBase.getConnection();
        }
    }

}
