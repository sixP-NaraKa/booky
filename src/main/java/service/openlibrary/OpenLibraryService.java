package service.openlibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.openlibrary.model.OpenLibraryBook;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenLibraryService {

    private static final String MAIN_BOOKS_API_URI = "https://openlibrary.org/api/books?";

    private static final String BOOKS_API_URL = MAIN_BOOKS_API_URI + "bibkeys=ISBN:%s&jscmd=data&format=json";

    private static final HttpClient client = HttpClient.newHttpClient();

    public static OpenLibraryBook getBookByIsbn(Long isbn) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BOOKS_API_URL.formatted(isbn)))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final String jsonString = response.body().replace("ISBN:%s".formatted(isbn), "ISBN");
            return new ObjectMapper().readValue(jsonString, OpenLibraryBook.class);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
