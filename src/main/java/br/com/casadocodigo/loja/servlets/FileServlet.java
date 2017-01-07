package br.com.casadocodigo.loja.servlets;

import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.casadocodigo.loja.infra.FileServerSave;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = 5366244905779362727L;

	protected void service(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
    String path = req.getRequestURI().split("/file")[1];

    Path source = Paths.get(FileServerSave.SERVER_PATH + "/" + path);
    FileNameMap fileNameMap = URLConnection.getFileNameMap();
    String contentType = fileNameMap.getContentTypeFor("file:"+source);

    res.reset();
    res.setContentType(contentType);    
    res.setContentLengthLong(Files.size(source));
    res.setHeader("Content-Disposition", 
            "filename=\""+source.getFileName().toString() + "\"");
    FileServerSave.transfer(source, res.getOutputStream());
}

}
