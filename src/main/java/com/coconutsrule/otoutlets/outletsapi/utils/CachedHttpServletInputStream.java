package com.coconutsrule.otoutlets.outletsapi.utils;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CachedHttpServletInputStream extends ServletInputStream {
    private final InputStream cachedContentInputStream;
    ReadListener readListener;

    @Override
    public boolean isFinished() {
        try {
            return cachedContentInputStream.available() == 0;
        } catch (IOException e) {
            return true;
        }
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener listener) {
       this.readListener = listener;
    }

    @Override
    public int read() throws IOException {
        return cachedContentInputStream.read();
    }
    
}
