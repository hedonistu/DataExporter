public interface CsvValidate {
    void csvValidate();
    //# check 1 (hotel name) @ contains only utf-8 characters
    boolean csvValidateHotelName(String hotel_name);
    //# check 2 (hotel url) @ valid url
    boolean csvValidateHotelUrl(String hotel_url);
    //# check 3 (stars_ @ number between 0 and 5
    boolean csvValidateHotelRating(String hotel_url);
}
