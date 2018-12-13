package com.example.administrator.helloworld.mock;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class ListTest {

    @Test
    public void testGet() throws Exception {
        List mockedList = Mockito.mock(List.class);
        Mockito.when(mockedList.get(0)).thenReturn("one");
        String str = (String) mockedList.get(0);
        Assert.assertTrue("one".equals(str));
        Assert.assertTrue(mockedList.size() == 0);
        Mockito.verify(mockedList).get(0);
    }
}
