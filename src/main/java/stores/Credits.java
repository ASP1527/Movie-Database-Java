package stores;

import structures.*;

import interfaces.ICredits;

public class Credits implements ICredits {

    /**
     * The constructor for the Credits data store. This is where you should
     * initialise your data structures.
     */
    HashTable credits;
    LinkedList movies;
    LinkedList queue;
    public Credits() {
        credits = new HashTable(1000);
        movies = new LinkedList<>();
    }

    /**
     * Adds data about the people who worked on a given film
     * 
     * @param cast An array of all cast members that starred in the given film
     * @param crew An array of all crew members that worked on a given film
     * @param id   The movie ID
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(Cast[] cast, Crew[] crew, int id) {
        // checks if the movie is already in the table
        if (credits.find(id)) {
            return false;
        }
        // creates an array that holds objects
        Object[] toAdd = new Object[3];
        // wraps and adds the data
        Cast[] addCast = cast;
        Crew[] addCrew = crew;
        Integer addID = id;
        toAdd[0] = addID;
        toAdd[1] = cast;
        toAdd[2] = crew;
        credits.add(toAdd);
        // adds data to the linked list holding fil ids
        Object[] movie = new Object[1];
        movie[0] = addID;
        movies.add(movie);
        return true;
    }

    /**
     * Remove a given films data from the data structure
     * 
     * @param id The movie ID
     * @return TRUE if the data was removed, FALSE otherwise
     */
    @Override
    public boolean remove(int id) {
        // checks if id is found
        if (credits.find(id)) {
            // removes data from both linked list and hashtable
            credits.remove(id);
            movies.remove(id);
            return true;
        }
        // returns false if id wasn't valid
        return false;
    }

    /**
     * Gets all the IDs for all films
     * 
     * @return An array of all film IDs
     */
    @Override
    public int[] getFilmIDs() {
        // array that holds the ids
        int[] ids = new int[movies.size()];
        // adds ids to the array
        for (int i=0; i < movies.size(); i++) {
            ids[i] = (int)movies.get(i)[0];
        }
        // returns the array
        return ids;
    }

    /**
     * Gets all the films worked on by a given cast ID (not cast element ID)
     * 
     * @param castID The ID of the cast member to be found
     * @return An array of film IDs relating to all films worked on by the requested
     *         cast member. If the cast member cannot be found, then return null
     */
    @Override
    public int[] getFilmIDsFromCastID(int castID) {
        // sets an initial array of size all movies
        int[] ids = new int[movies.size()];
        // var to hold the size of the array to return
        int count = 0;
        // goes through each movie
        for (int i=0; i < movies.size(); i++) {
            // gets cast and id of movie
            Cast[] cast = (Cast[])credits.get((int)movies.get(i)[0])[1];
            int id = (int)credits.get((int)movies.get(i)[0])[0];
            // goes through each cast member and increments count and adds the film id if it matches castID
            for (int j=0; j < cast.length; j++) {
                if (cast[j].getID() == castID) {
                    ids[count] = id;
                    count ++;
                    break;
                }
            }
        }
        // if no cast was found, return an empty array
        if (count == 0) {
            return new int[0];
        }
        // make a new return array the size of the number of filmIDs found
        int[] ret = new int[count];
        for (int i=0; i < count; i++) {
            ret[i] = ids[i];
        }
        // return the filmIDs
        return ret;
    }

    /**
     * Gets all the films worked on by a given crew ID (not crew element ID)
     * 
     * @param crewID The ID of the cast member to be found
     * @return An array of film IDs relating to all films worked on by the requested
     *         crew member. If the crew member cannot be found, then return null
     */
    @Override
    public int[] getFilmIDsFromCrewID(int crewID) {
        // sets an initial array of size all movies
        int[] ids = new int[movies.size()];
        // var to hold the size of the array to return
        int count = 0;
        // goes through each movie
        for (int i=0; i < movies.size(); i++) {
            // gets crew and id of movie
            Crew[] crew = (Crew[])credits.get((int)movies.get(i)[0])[2];
            int id = (int)credits.get((int)movies.get(i)[0])[0];
            // goes through each crew member and increments count and adds the film id if it matches crewID
            for (int j=0; j < crew.length; j++) {
                if (crew[j].getID() == crewID) {
                    ids[count] = id;
                    count ++;
                    break;
                }
            }
        }
        // if no crew was found, return an empty array
        if (count == 0) {
            return new int[0];
        }
        // make a new return array the size of the number of filmIDs found
        int[] ret = new int[count];
        for (int i=0; i < count; i++) {
            ret[i] = ids[i];
        }
        // return the filmIDs
        return ret;
    }

    /**
     * Gets all the cast that worked on a given film
     * 
     * @param filmID The movie ID
     * @return An array of Cast objects for all people that worked on a requested
     *         film. If the film cannot be found, then return null
     */
    @Override
    public Cast[] getCast(int filmID) {
        // returns null if film isn't found
        if (!credits.find(filmID)) {
            return null;
        }
        // returns the cast
        return (Cast[])credits.get(filmID)[1];
    }

    /**
     * Gets all the cast that worked on a given film
     * 
     * @param filmID The movie ID
     * @return An array of Cast objects for all people that worked on a requested
     *         film. If the film cannot be found, then return null
     */
    @Override
    public Crew[] getCrew(int filmID) {
        // returns null if film isn't found
        if (!credits.find(filmID)) {
            return null;
        }
        // returns the crew
        return (Crew[])credits.get(filmID)[2];
    }

    /**
     * Gets the number of cast that worked on a given film
     * 
     * @param filmID The movie ID
     * @return The number of cast member that worked on a given film. If the film
     *         cannot be found, then return -1
     */
    @Override
    public int sizeOfCast(int filmID) {
        // if film cant be found return null
        if (getCast(filmID) == null) {
            return -1;
        }
        // return length of cast from getCast
        return getCast(filmID).length;
    }

    /**
     * Gets the number of crew that worked on a given film
     * 
     * @param filmID The movie ID
     * @return The number of crew member that worked on a given film. If the film
     *         cannot be found, then return -1
     */
    @Override
    public int sizeofCrew(int filmID) {
        // if film cant be found return null
        if (getCrew(filmID) == null) {
            return -1;
        }
        // return length of cast from getCrew
        return getCrew(filmID).length;
    }

    /**
     * Gets the number of films stored in this data structure
     * 
     * @return The number of films in the data structure
     */
    @Override
    public int size() {
        // get number of movie ids
        return movies.size();
    }

    /**
     * Gets the cast name for a given cast ID
     * 
     * @param castID The ID of the cast member to be found
     * @return The name of the cast member for the given ID. If the ID is invalid,
     *         then null should be returned
     */
    @Override
    public String getCastName(int castID) {
        // goes through each movie
        for (int i=0; i < movies.size(); i++) {
            // gets cast and id of movie
            Cast[] cast = (Cast[])credits.get((int)movies.get(i)[0])[1];
            // goes through each cast member and returns their name if it matches castID
            for (int j=0; j < cast.length; j++) {
                if (cast[j].getID() == castID) {
                    return cast[j].getName();
                }
            }
        }
        // returns null if not found
        return null;
    }

    /**
     * Gets the crew name for a given crew ID
     * 
     * @param crewID The ID of the crew member to be found
     * @return The name of the crew member for the given ID. If the ID is invalid,
     *         then null should be returned
     */
    @Override
    public String getCrewName(int crewID) {
        // goes through each movie
        for (int i=0; i < movies.size(); i++) {
            // gets crew and id of movie
            Crew[] crew = (Crew[])credits.get((int)movies.get(i)[0])[2];
            // goes through each crew member and returns their name if it matches crewID
            for (int j=0; j < crew.length; j++) {
                if (crew[j].getID() == crewID) {
                    return crew[j].getName();
                }
            }
        }
        // returns null if not found
        return null;
    }

    /**
     * Gets a list of all unique cast IDs present in the data structure
     * 
     * @return An array of all unique cast IDs. If there are no cast IDs, then
     *         return an empty array
     */
    @Override
    public int[] getUniqueCastIDs() {
        // gets all films
        int[] films = getFilmIDs();
        // holds unique ids
        int[] buffer = new int[1000];
        // holds number of unique people
        int count = 0;
        // sets everything to -1 by default
        for (int i=0; i < buffer.length; i++) {
            buffer[i] = - 1;
        }
        // go through all of the movies
        for (int i=0; i < films.length; i++) {
            // gets the cast for the movie
            Cast[] cast = getCast(films[i]);
            // goes through the cast
            for (int j=0; j < cast.length; j++) {
                // var to check if member is found
                boolean found = false;
                // goes through the ids to see if its already in the array
                for (int k=0; k < buffer.length; k++) {
                    if (cast[j].getID() == buffer[k]) {
                        found = true;
                        break;
                    }
                }
                // if the id was not found
                if (!found) {
                    // doubles the size of the array if it is full
                    if (buffer[buffer.length-1] != -1) {
                        int size = buffer.length * 2;
                        int[] temp = buffer;
                        buffer = new int[size];
                        for (int k=0; k < buffer.length; k++) {
                            buffer[k] = - 1;
                        }
                        for (int l=0; l < temp.length; l++) {
                            buffer[l] = temp[l];
                        }
                    }
                    // adds the new id in the next empty position
                    for (int k=0; k < buffer.length; k++) {
                        if (buffer[k] == -1) {
                            buffer[k] = cast[j].getID();
                            break;
                        }
                    }
                    // increments the number of unique people
                    count ++;
                }
            }
        }
        // removes any null/empty entries
        int[] ret = new int[count];
        count = 0;
        for (int i=0; i < buffer.length; i++) {
            if (buffer[i] != -1) {
                ret[count] = buffer[i];
                count ++;
            }
        }
        // returns an empty array if count is 0 otherwise it returns the ids of unique members
        return ret;
    }

    /**
     * Gets a list of all unique crew IDs present in the data structure
     * 
     * @return An array of all unique crew IDs. If there are no crew IDs, then
     *         return an empty array
     */
    @Override
    public int[] getUniqueCrewIDs() {
        // gets all films
        int[] films = getFilmIDs();
        // holds unique ids
        int[] buffer = new int[1000];
        // holds number of unique people
        int count = 0;
        // sets everything to -1 by default
        for (int i=0; i < buffer.length; i++) {
            buffer[i] = - 1;
        }
        // go through all of the movies
        for (int i=0; i < films.length; i++) {
            // gets the crew for the movie
            Crew[] crew = getCrew(films[i]);
            // goes through the crew
            for (int j=0; j < crew.length; j++) {
                // var to check if member is found
                boolean found = false;
                // goes through the ids to see if its already in the array
                for (int k=0; k < buffer.length; k++) {
                    if (crew[j].getID() == buffer[k]) {
                        found = true;
                        break;
                    }
                }
                // if the id was not found
                if (!found) {
                    // doubles the size of the array if it is full
                    if (buffer[buffer.length-1] != -1) {
                        int size = buffer.length * 2;
                        int[] temp = buffer;
                        buffer = new int[size];
                        for (int k=0; k < buffer.length; k++) {
                            buffer[k] = - 1;
                        }
                        for (int l=0; l < temp.length; l++) {
                            buffer[l] = temp[l];
                        }
                    }
                    // adds the new id in the next empty position
                    for (int k=0; k < buffer.length; k++) {
                        if (buffer[k] == -1) {
                            buffer[k] = crew[j].getID();
                            break;
                        }
                    }
                    // increments the number of unique people
                    count ++;
                }
            }
        }
        // removes any null/empty entries
        int[] ret = new int[count];
        count = 0;
        for (int i=0; i < buffer.length; i++) {
            if (buffer[i] != -1) {
                ret[count] = buffer[i];
                count ++;
            }
        }
        // returns an empty array if count is 0 otherwise it returns the ids of unique members
        return ret;
    }

    /**
     * Get all the cast members that have the given string within their name
     * 
     * @param cast The string that needs to be found
     * @return An array of Cast objects of all cast members that have the requested
     *         string in their name
     */
    @Override
    public Cast[] findCast(String cast) {
        // list to hold all cast
        Cast[] castList = new Cast[movies.size()];
        // var to hold position in the above array
        int count = 0;
        // goes through each movie
        for (int i=0; i < movies.size(); i++) {
            // gets cast and id of movie
            Cast[] person = (Cast[])credits.get((int)movies.get(i)[0])[1];
            // goes through each cast member and returns their name if it matches castID
            for (int j=0; j < person.length; j++) {
                // checks if cast is already in the list
                boolean found = false;
                for (int k=0; k < count; k++) {
                    if (castList[k].getName().contains(cast)) {
                        found = true;
                    }
                }
                // add the cast member if they aren't already in the array
                if (!found) {
                    if (person[j].getName().contains(cast)) {
                        castList[count] = person[j];
                        count ++;
                    }
                }
            }
        }
        if (count == 0) {
            // returns null if not found
            return new Cast[0];
        }
        // removes all null elements by adding the elements to a new array
        Cast[] ret = new Cast[count];
        for (int i=0; i < count; i++) {
            ret[i] = castList[i];
        }
        return ret;
    }

    /**
     * Get all the crew members that have the given string within their name
     * 
     * @param crew The string that needs to be found
     * @return An array of Crew objects of all crew members that have the requested
     *         string in their name
     */
    @Override
    public Crew[] findCrew(String crew) {
        // list to hold all crew
        Crew[] crewList = new Crew[movies.size()];
        // var to hold position in the above array
        int count = 0;
        // goes through each movie
        for (int i=0; i < movies.size(); i++) {
            // gets crew and id of movie
            Crew[] person = (Crew[])credits.get((int)movies.get(i)[0])[2];
            // goes through each crew member and returns their name if it matches crewID
            for (int j=0; j < person.length; j++) {
                // checks if crew is already in the list
                boolean found = false;
                for (int k=0; k < count; k++) {
                    if (crewList[k].getName().contains(crew)) {
                        found = true;
                    }
                }
                // add the crew member if they aren't already in the array
                if (!found) {
                    if (person[j].getName().contains(crew)) {
                        crewList[count] = person[j];
                        count ++;
                    }
                }
            }
        }
        if (count == 0) {
            // returns null if not found
            return new Crew[0];
        }
        // removes all null elements by adding the elements to a new array
        Crew[] ret = new Crew[count];
        for (int i=0; i < count; i++) {
            ret[i] = crewList[i];
        }
        return ret;
    }

    /**
     * Finds all stars. A star is the following person: a star actor is
     * a cast member who have appeared in 3 or more movies, where each movie
     * has an average score of 4 or higher.
     * 
     * @param ratings The ratings for all films
     * @return An array of Cast IDs that are stars
     */
    @Override
    public int[] findStarCastID(Ratings ratings) {
        // array to hold the film ids with ratings > 4
        int[] ids = new int[movies.size()];
        // var to hold position of added elements
        int count = 0;
        // adds a movie if its ratings >= 4
        for (int i=0; i < movies.size(); i++) {
            if ((float)ratings.getMovieAverageRatings((int)movies.get(i)[0]) >= 4f) {
                ids[count] = (int)movies.get(i)[0];
                count ++;
            }
        }
        // removes empty elements of the ids array
        int[] tempID = new int[count];
        for (int i=0; i < count; i++) {
            tempID[i] = ids[i];
        }
        ids = tempID;
        // resets count
        count = 0;
        // buffer to hold the star cast
        int[] buffer = new int[1000];
        // goes through all of the movies rated >= 4
        for (int i=0; i < ids.length; i++) {
            // gets the cast for the movie
            Cast[] cast = getCast(ids[i]);
            // goes through the cast
            for (int j=0; j < cast.length; j++) {
                // var to hold the number of other movies with rating >=4 thery're in
                int num = 0;
                // goes through all of the movies and their casts to see if this cast member is in more than three movies
                for (int k=0; k < ids.length; k++) {
                    for (int l=0; l < getCast(ids[k]).length; l++) {
                        // checks if the cast member of the movie matches the current member
                        if (getCast(ids[k])[l].getID() == cast[j].getID()) {
                            // increments num
                            num ++;
                        }
                    }
                }
                // runs if the member is in 3 or more movies
                if (num >= 3) {
                    // checks if the member is already in the array
                    boolean found = false;
                    for (int k=0; k < buffer.length; k++) {
                        if (buffer[k] == cast[j].getID()) {
                            found = true;
                        }
                    }
                    // adds cast member to the array
                    if (!found) {
                        // doubles the size of the array if it is full
                        if (buffer[buffer.length-1] == 0) {
                            int size = buffer.length * 2;
                            int[] temp = buffer;
                            buffer = new int[size];
                            for (int l=0; l < temp.length; l++) {
                                buffer[l] = temp[l];
                            }
                        }
                        // adds the member to the array
                        buffer[count] = cast[j].getID();
                        count ++;
                    }
                }
            }
        }
        // removes any null/empty entries
        int[] ret = new int[count];
        for (int i=0; i < count; i++) {
            ret[i] = buffer[i];
        }
        // returns an empty array if count is 0 otherwise it returns the array of cast members that fit the star requirements
        return ret;
    }

    /**
     * Finds all superstars. A superstar is the following person: a star actor is
     * also a superstar if they have played in at least two movies with another star
     * actor.
     * 
     * @param ratings The ratings for all films
     * @return An array of Cast IDs that are super stars
     */
    @Override
    public int[] findSuperStarCastID(Ratings ratings) {
        // gets all the stars
        int[] stars = findStarCastID(ratings);
        int[] buffer = new int[500];
        int count = 0;
        // for each star
        for (int i=0; i < stars.length; i++) {
            int starCount = 0;
            // gets the movies that the star is in
            int[] movie = getFilmIDsFromCastID(stars[i]);
            // for every movie the star is in
            for (int j=0; j < movie.length; j++) {
                // for every cast in the movie
                Cast[] cast = getCast(movie[j]);
                boolean tempStarCount = false;
                // moves to the next movie once they have stared with a star in this movie
                for (int k=0; k < cast.length; k++) {
                    // if the cast member is a star and not themselves, add them to the list of superstars
                    for (int l=0; l < stars.length; l++) {
                        if (cast[k].getID() != stars[i] & cast[k].getID() == stars[l]) {
                            System.out.println("current star");
                            System.out.println(stars[i]);
                            System.out.println("costar");
                            System.out.println(stars[l]);
                            System.out.println("movie");
                            System.out.println(movie[j]);
                            tempStarCount = true;
                            break;
                        }
                    }
                }
                if (tempStarCount) {
                    starCount ++;
                }
            }
            // if they're in 2 or movies with other stars
            if (starCount >= 2) {
                // checks if the star is already in the array
                boolean found = false;
                for (int k=0; k < buffer.length; k++) {
                    if (buffer[k] == stars[i]) {
                        found = true;
                    }
                }
                // adds star to the array
                if (!found) {
                    // doubles the size of the array if it is full
                    if (buffer[buffer.length-1] == 0) {
                        int size = buffer.length * 2;
                        int[] temp = buffer;
                        buffer = new int[size];
                        for (int l=0; l < temp.length; l++) {
                            buffer[l] = temp[l];
                        }
                    }
                    // adds star to the array
                    buffer[count] = stars[i];
                    count ++;
                }
            }
        }
        // removes any null/empty entries
        int[] ret = new int[count];
        for (int i=0; i < count; i++) {
            ret[i] = buffer[i];
        }
        // returns an empty array if count is 0 otherwise it returns the array of cast members that fit the superstar requirements
        return ret;
    }

    /**
     * Finds the distance between cast members A and B, by looking at common cast
     * members in films. For example, if A and B were in different movies, but both
     * started in a movie with cast member C, then there distance would be 1.
     * 
     * @param castIDA The starting cast member
     * @param castIDB The finishing cast member
     * @return If there is no connection, then return an empty array. If castIDA ==
     *         castIDB, then return an array containing ONLY castIDB. If there is a
     *         path from castIDA to castIDB, then all cast IDs in the path should be
     *         listed in order in the returned array, including castIDB. In the
     *         above example, the array should return {castIDC, castIDB}.
     */
    @Override
    public int[] findDistance(int castIDA, int castIDB) {
        if (castIDA == castIDB) {
            int[] ret = {castIDB};
            return ret;
        }
        queue = new LinkedList<>();
        int count = 0;
        int incount = 0;
        int[] inserted = new int[1000];
        int[] seen = new int[1000];

        boolean found = false;

        Integer index = castIDA;
        Object[] o = {index};
        queue.add(o);
        inserted[0] = castIDA;
        incount ++;
        // BFS
        while (queue.size() != 0 & !found) {
            int data = (int)queue.get(0)[0];
            queue.removeHead();
            seen[count] = data;
            count ++;
            int[] temp = getIDs(getFilmIDsFromCastID(data));
            for (int i=0; i < getIDs(getFilmIDsFromCastID(data)).length; i++) {
                boolean in = false;
                for (int j=0; j < inserted.length; j++) {
                    if (temp[i] == inserted[j]) {
                        in = true;
                    }
                }
                if (!in) {
                    // checks if castb has been found
                    if ((int)index == castIDB) {
                        seen[count] = (int)index;
                        found = true;
                    }
                    // adds cast to tail
                    index = temp[i];
                    o = new Object[1];
                    o[0] = index;
                    queue.addTail(o);
                    inserted[incount] = temp[i];
                    incount ++;
                }
            }
        }
        if (count == 0) {
            return new int[0];
        }
        // removes any null/empty entries
        int[] ret = new int[count];
        for (int i=0; i < count-1; i++) {
            ret[i] = seen[i+1];
        }
        ret[count-1] = castIDB;
        return ret;
    }

    private int[] getIDs(int[] films) {
        // gets unique ids of cast from a list of films
        int[] buffer = new int[1000];
        int count = 0;
        for (int i=0; i < films.length; i++) {
            Cast[] cast = getCast(films[i]);
            for (int j=0; j < cast.length; j++) {
                boolean found = false;
                for (int k=0; k < buffer.length; k++) {
                        if (buffer[k] == cast[j].getID()) {
                            found = true;
                        }
                }
                if (!found) {
                    // doubles the size of the array if it is full
                    if (buffer.length == count) {
                        int size = buffer.length + 50;
                        int[] temp = buffer;
                        buffer = new int[size];
                        for (int l=0; l < temp.length; l++) {
                            buffer[l] = temp[l];
                        }
                    }
                    buffer[count] = cast[i].getID();
                    count ++;
                }
            }
        }
        // removes any null/empty entries
        int[] ret = new int[count];
        for (int i=0; i < count; i++) {
            ret[i] = buffer[i];
        }
        return ret;
    }

}
