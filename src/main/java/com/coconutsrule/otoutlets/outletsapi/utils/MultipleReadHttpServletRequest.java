package com.coconutsrule.otoutlets.outletsapi.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.yaml.snakeyaml.reader.StreamReader;

/**
 * HttpServletRequest wrappper that allows multiple reads.
 * TODO: Implement a version of this class that does not involve keeping all of the cached content
 * in memory
 */
public class MultipleReadHttpServletRequest extends HttpServletRequestWrapper{
    byte[] cachedContent;

    public MultipleReadHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        cachedContent = request.getInputStream().readAllBytes();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        InputStream stream = new ByteArrayInputStream(cachedContent);
        
        return new CachedHttpServletInputStream(stream);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return  new BufferedReader(new InputStreamReader(getInputStream()));
    }    
}
