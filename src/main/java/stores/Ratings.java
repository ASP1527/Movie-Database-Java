package stores;

import java.util.Calendar;

import interfaces.IRatings;
import structures.*;

public class Ratings implements IRatings {

    /**
     * The constructor for the Ratings data store. This is where you should
     * initialise your data structures.
     */
    LinkedList movies;
    public Ratings() {
        movies = new LinkedList<>();
    }

    /**
     * Adds a rating to the data structure. The rating is made unique by its user ID
     * and its movie ID
     * 
     * @param userID    The user ID
     * @param movieID   The movie ID
     * @param rating    The rating gave to the film by this user (between 0 and 5
     *                  inclusive)
     * @param timestamp The time at which the rating was made
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(int userID, int movieID, float rating, Calendar timestamp) {
        // creates an object to hold the data
        Object[] toAdd = new Object[4];
        // wraps the data into wrapper classes
        Integer addUserID = userID;
        Integer addMovieID = movieID;
        Float addRating = rating;
        Calendar addTimestamp = timestamp;
        // adds the data to the array of objects
        toAdd[0] = addUserID;
        toAdd[1] = addMovieID;
        toAdd[2] = addRating;
        toAdd[3] = addTimestamp;
        movies.add(toAdd);
        return true;
    }

    /**
     * Removes a given rating, using the user ID and the movie ID as the unique
     * identifier
     * 
     * @param userID  The user ID
     * @param movieID The movie ID
     * @return TRUE if the data was removed successfully, FALSE otherwise
     */
    @Override
    public boolean remove(int userID, int movieID) {
        // remove the rating from the table
        return movies.removeComposite(userID, movieID);
    }

    /**
     * Sets a rating for a given user ID and movie ID. Therefore, should the given
     * user have already rated the given movie, the new data should overwrite the
     * existing rating. However, if the given user has not already rated the given
     * movie, then this rating should be added to the data structure
     * 
     * @param userID    The user ID
     * @param movieID   The movie ID
     * @param rating    The new rating to be given to the film by this user (between
     *                  0 and 5 inclusive)
     * @param timestamp The time at which the rating was made
     * @return TRUE if the data able to be added/updated, FALSE otherwise
     */
    @Override
    public boolean set(int userID, int movieID, float rating, Calendar timestamp) {
        // removes the item if it exists
        remove(userID, movieID);
        // adds the updated/new rating
        add(userID, movieID, rating, timestamp);
        return false;
    }

    /**
     * Find all ratings between a given start date and end date. If a rating falls
     * exactly on a given start date or a given end date, then this should not be
     * included
     * 
     * @param start The start time for the range
     * @param end   The end time for the range
     * @return An array of ratings between start and end. If there are no ratings,
     *         then return an empty array
     */
    @Override
    public float[] getRatingsBetween(Calendar start, Calendar end) {
        // gets an array of all ratings
        Object[][] all = movies.getAll();
        // holds buffer array
        float[] buffer = new float[all.length];
        // var to check if there is a rating in the range
        int rating = 0;
        // goes through the array of arrays
        for (int i=0; i < all.length; i++) {
            // gets the array, rating and timestamp
            Object[] item = all[i];
            float temp = (float)item[2];
            Calendar date = (Calendar)item[3];
            // adds the rating if it's in the range
            if (date.compareTo(start) > 0 & date.compareTo(end) < 0) {
                // adds the rating to the return array in the next empty position
                buffer[rating] = temp;
                rating ++;
            }
        }
        // checks if there were no ratings
        if (rating == 0) {
            return new float[0];
        }
        // creates a return array of length rating (how many ratings there are)
        float[] ret = new float[rating];
        for (int j=0; j < rating; j++) {
            ret[j] = buffer[j];
        }
        return ret;
    }

    /**
     * Find all ratings for a given film, between a given start date and end date.
     * If a rating falls exactly on a given start date or a given end date, then
     * this should not be included
     * 
     * @param movieID The movie ID
     * @param start   The start time for the range
     * @param end     The end time for the range
     * @return An array of ratings between start and end for a given film. If there
     *         are no ratings, then return an empty array
     */
    @Override
    public float[] getMovieRatingsBetween(int movieID, Calendar start, Calendar end) {
        // gets the data
        Object[][] all = movies.getRow(movieID, 1);
        // holds buffer array
        float[] buffer = new float[all.length];
        // var to check if there is a rating in the range
        int rating = 0;
        // goes through the array of arrays
        for (int i=0; i < all.length; i++) {
            // gets the array, rating and timestamp
            Object[] item = all[i];
            float temp = (float)item[2];
            Calendar date = (Calendar)item[3];
            // adds the rating if it's in the range
            if (date.compareTo(start) > 0 & date.compareTo(end) < 0) {
                // adds the rating to the return array in the next empty position
                buffer[rating] = temp;
                rating ++;
            }
        }
        if (rating == 0) {
            return new float[0];
        }
        // creates a return array of length rating (how many ratings there are)
        float[] ret = new float[rating];
        for (int j=0; j < rating; j++) {
            ret[j] = buffer[j];
        }
        return ret;
    }

    /**
     * Find all ratings for a given user, between a given start date and end date.
     * If a rating falls exactly on a given start date or a given end date, then
     * this should not be included
     * 
     * @param userID The user ID
     * @param start  The start time for the range
     * @param end    The end time for the range
     * @return An array of ratings between start and end for a given user. If there
     *         are no ratings, then return an empty array
     */
    @Override
    public float[] getUserRatingsBetween(int userID, Calendar start, Calendar end) {
        // gets the data
        Object[][] all = movies.getRow(userID, 0);
        // holds buffer array
        float[] buffer = new float[all.length];
        // var to check if there is a rating in the range
        int rating = 0;
        // goes through the array of arrays
        for (int i=0; i < all.length; i++) {
            // gets the array, rating and timestamp
            Object[] item = all[i];
            float temp = (float)item[2];
            Calendar date = (Calendar)item[3];
            // adds the rating if it's in the range
            if (date.compareTo(start) > 0 & date.compareTo(end) < 0) {
                // adds the rating to the return array in the next empty position
                buffer[rating] = temp;
                rating ++;
            }
        }
        if (rating == 0) {
            return new float[0];
        }
        // creates a return array of length rating (how many ratings there are)
        float[] ret = new float[rating];
        for (int j=0; j < rating; j++) {
            ret[j] = buffer[j];
        }
        return ret;
    }

    /**
     * Get all the ratings for a given film
     * 
     * @param movieID The movie ID
     * @return An array of ratings. If there are no ratings or the film cannot be
     *         found, then return an empty array
     */
    @Override
    public float[] getMovieRatings(int movieID) {
        // gets the data
        Object[][] all = movies.getRow(movieID, 1);
        // holds return array
        float[] ret = new float[all.length];
        // goes through the array of arrays
        for (int i=0; i < all.length; i++) {
            // gets the array and the rating
            Object[] item = all[i];
            float temp = (float)item[2];
            // adds the rating to the return array
            ret[i] = temp;
        }
        return ret;
    }

    /**
     * Get all the ratings for a given user
     * 
     * @param userID The user ID
     * @return An array of ratings. If there are no ratings or the user cannot be
     *         found, then return an empty array
     */
    @Override
    public float[] getUserRatings(int userID) {
        // gets the data
        Object[][] all = movies.getRow(userID, 0);
        // holds return array
        float[] ret = new float[all.length];
        // goes through the array of arrays
        for (int i=0; i < all.length; i++) {
            // gets the array and the rating
            Object[] item = all[i];
            float temp = (float)item[2];
            // adds the rating to the return array
            ret[i] = temp;
        }
        return ret;
    }

    /**
     * Get the average rating for a given film
     * 
     * @param movieID The movie ID
     * @return Produces the average rating for a given film. If the film cannot be
     *         found, or there are no rating, return 0
     */
    @Override
    public float getMovieAverageRatings(int movieID) {
        // gets all the ratings for the movie
        float[] ratings = getMovieRatings(movieID);
        // if there are no ratings, return 0
        if (ratings.length == 0) {
            return 0;
        }
        // buffer to hold the total
        float total = 0;
        // add each rating to the total
        for (int i=0; i < ratings.length; i++) {
            total += ratings[i];
        }
        // return the average
        return total / ratings.length;
    }

    /**
     * Get the average rating for a given user
     * 
     * @param userID The user ID
     * @return Produces the average rating for a given user. If the user cannot be
     *         found, or there are no rating, return 0
     */
    @Override
    public float getUserAverageRatings(int userID) {
        // gets all the ratings for the user
        float[] ratings = getUserRatings(userID);
        // if there are no ratings, return 0
        if (ratings.length == 0) {
            return 0;
        }
        // buffer to hold the total
        float total = 0;
        // add each rating to the total
        for (int i=0; i < ratings.length; i++) {
            total += ratings[i];
        }
        // return the average
        return total / ratings.length;
    }

    /**
     * Gets the top N films with the most ratings, in order from most to least
     * 
     * @param num The number of films that should be returned
     * @return A sorted array of film IDs with the most ratings. The array should be
     *         no larger than num. If there are less than num films in the store,
     *         then the array should be the same length as the number of films
     */
    @Override
    public int[] getTopMovies(int num) {
        // gets an array of all ratings
        Object[][] all = movies.getAll();
        // sets 2 arrays
        // holds the number of ratings
        int[] ratings = new int[all.length];
        // holds the ids
        int[] ids = new int[all.length];
        // variable to hold how many items are in the arrays
        int count = 0;
        // loops through all ratings
        for (int i=0; i < all.length; i++) {
            // checks if the movie has already been added
            boolean found = false;
            for (int j=0; j < ratings.length; j++) {
                if (ids[j] == (int)all[i][1]) {
                    found = true;
                }
            }
            // adds the movie if it's not already in the array in the next empty poaition
            if (!found) {
                ids[count] = (int)all[i][1];
                ratings[count] = getMovieRatings(ids[count]).length;
                count += 1;
            }
        }
        // bubble sort; swaps elements if the previous one is smaller than the next one
        for (int i=0; i < (count + 1) - 1; i++) {
            for (int j=0; j < (count + 1) - 1 - i; j++) {
                if (ratings[j] < ratings[j+1]) {
                    int temp = ratings[j];
                    int temp2 = ids[j];
                    ratings[j] = ratings[j+1];
                    ids[j] = ids[j+1];
                    ratings[j+1] = temp;
                    ids[j+1] = temp2;
                }
            }
        }
        // variable to check if the number of movies < num
        boolean otherReturn = false;
        // adds items to a new list to return of size num
        int[] ret = new int[num];
        for (int i=0; i < num; i++) {
            ret[i] = ids[i];
            // checks if the list ran out of items
            if (ratings[i] == 0 & ids[i] == 0) {
                otherReturn = true;
            }
        }
        // normal return
        if (!otherReturn) {
            return ret;
        }
        // creates a new array of size count which is < num and returns this list with ids
        ret = new int[count+1];
        for (int i=0; i < count + 1; i++) {
            ret[i] = ids[i];
        }
        return ret;
    }

    /**
     * Gets the top N users with the most ratings, in order from most to least
     * 
     * @param num The number of users that should be returned
     * @return A sorted array of user IDs with the most ratings. The array should be
     *         no larger than num. If there are less than num users in the store,
     *         then the array should be the same length as the number of users
     */
    @Override
    public int[] getMostRatedUsers(int num) {
        // gets an array of all ratings
        Object[][] all = movies.getAll();
        // sets 2 arrays
        // holds the number of ratings
        int[] ratings = new int[all.length];
        // holds the ids
        int[] ids = new int[all.length];
        // variable to hold how many items are in the arrays
        int count = 0;
        // loops through all ratings
        for (int i=0; i < all.length; i++) {
            // checks if the user has already been added
            boolean found = false;
            for (int j=0; j < ratings.length; j++) {
                if (ids[j] == (int)all[i][0]) {
                    found = true;
                }
            }
            // adds the movie if it's not already in the array in the next empty poaition
            if (!found) {
                ids[count] = (int)all[i][0];
                ratings[count] = getUserRatings(ids[count]).length;
                count += 1;
            }
        }
        // bubble sort; swaps elements if the previous one is smaller than the next one
        for (int i=0; i < (count + 1) - 1; i++) {
            for (int j=0; j < (count + 1) - 1 - i; j++) {
                if (ratings[j] < ratings[j+1]) {
                    int temp = ratings[j];
                    int temp2 = ids[j];
                    ratings[j] = ratings[j+1];
                    ids[j] = ids[j+1];
                    ratings[j+1] = temp;
                    ids[j+1] = temp2;
                }
            }
        }
        // variable to check if the number of movies < num
        boolean otherReturn = false;
        // adds items to a new list to return of size num
        int[] ret = new int[num];
        for (int i=0; i < num; i++) {
            ret[i] = ids[i];
            // checks if the list ran out of items
            if (ratings[i] == 0 & ids[i] == 0) {
                otherReturn = true;
            }
        }
        // normal return
        if (!otherReturn) {
            return ret;
        }
        // creates a new array of size count which is < num and returns this list with ids
        ret = new int[count+1];
        for (int i=0; i < count + 1; i++) {
            ret[i] = ids[i];
        }
        return ret;
    }

    /**
     * Gets the number of ratings in the data structure
     * 
     * @return The number of ratings in the data structure
     */
    @Override
    public int size() {
        return movies.size();
    }

}
