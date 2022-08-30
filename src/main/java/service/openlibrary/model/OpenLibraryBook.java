package service.openlibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Book entry model.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryBook implements Serializable {

    @JsonProperty(value = "ISBN")
    OpenLibraryBookData openLibraryBookData;

    public OpenLibraryBookData getOpenLibraryBookData() {
        return openLibraryBookData;
    }

    public void setOpenLibraryBookData(OpenLibraryBookData openLibraryBookData) {
        this.openLibraryBookData = openLibraryBookData;
    }

}
