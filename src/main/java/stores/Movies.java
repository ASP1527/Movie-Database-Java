package stores;

import java.util.Calendar;
import java.util.Date;

import interfaces.IMovies;
import structures.*;

public class Movies implements IMovies {

    /**
     * The constructor for the Movies data store. This is where you should
     * initialise your data structures.
     */
    HashTable hashtable, imdbhashtable;
    LinkedList collection, movies;
    Company[] company1;
    String[] string1;
    public Movies() {
        hashtable = new HashTable(1000);
        imdbhashtable = new HashTable(1000);
        collection = new LinkedList<>();
        movies = new LinkedList<>();
        company1 = new Company[1000];
        string1 = new String[1000];
    }

    /**
     * Adds data about a film to the data structure
     * 
     * @param id               The unique ID for the film
     * @param title            The English title of the film
     * @param originalTitle    The original language title of the film
     * @param overview         An overview of the film
     * @param tagline          The tagline for the film (empty string if there is no
     *                         tagline)
     * @param status           Current status of the film
     * @param genres           An array of Genre objects related to the film
     * @param release          The release date for the film
     * @param budget           The budget of the film in US Dollars
     * @param revenue          The revenue of the film in US Dollars
     * @param languages        An array of ISO 639 language codes for the film
     * @param originalLanguage An ISO 639 language code for the original language of
     *                         the film
     * @param runtime          The runtime of the film in minutes
     * @param homepage         The URL to the homepage of the film
     * @param adult            Whether the film is an adult film
     * @param video            Whether the film is a "direct-to-video" film
     * @param poster           The unique part of the URL of the poster (empty if
     *                         the URL is not known)
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(int id, String title, String originalTitle, String overview, String tagline, String status,
            Genre[] genres, Calendar release, long budget, long revenue, String[] languages, String originalLanguage,
            double runtime, String homepage, boolean adult, boolean video, String poster) {
        // TODO Build this function
        if (!hashtable.find(id)) {
            Object[] toAdd = new Object[21];
            Integer a = id;
            toAdd[0] = a;
            String b = title;
            toAdd[1] = b;
            String c = originalTitle;
            toAdd[2] = c;
            String d = overview;
            toAdd[3] = d;
            String e = tagline;
            toAdd[4] = e;
            String f = status;
            toAdd[5] = f;
            Genre[] g = genres;
            toAdd[6] = g;
            Calendar h = release;
            toAdd[7] = h;
            Long i = budget;
            toAdd[8] = i;
            Long j = revenue;
            toAdd[9] = j;
            String[] k = languages;
            toAdd[10] = k;
            String l = originalLanguage;
            toAdd[11] = l;
            Double m = runtime;
            toAdd[12] = m;
            String n = homepage;
            toAdd[13] = n;
            Boolean o = adult;
            toAdd[14] = o;
            Boolean p = video;
            toAdd[15] = p;
            String q = poster;
            toAdd[16] = q;
            String r = null;
            toAdd[17] = r;
            Double s = 0.0d;
            toAdd[18] = s;
            toAdd[19] = company1;
            toAdd[20] = string1;

            hashtable.add(toAdd);
            movies.add(toAdd);
            //[true, true, true, false, true, true, false, false, true, true, true, true, true, false, false, false, false, true, false, false]
            //[true, true, false, false, false, false, false, true, true, false, true, true, false, true, true, true, true, true, false, false]
            
            //<[hfzcgomett, igdftcofgn, tkednowwvd, gseamqibiy, bfcviayrqr, xrjeqiitxm, nbocxkzutc, ardfkfdaew, fcjpktywyh, cmmdgslysf, kugydulizm, hxcjsutaej, bsvmljnmlw, oilleshrxr, zjcevfnzou, kgodhobvpq, mkyjiqkxtc, zxwxiergtd, wbnwjxzcqw, vqchturjfx]>
            //<[kugydulizm, zxwxiergtd, zjcevfnzou, vqchturjfx, ardfkfdaew, mkyjiqkxtc, wbnwjxzcqw, bfcviayrqr, hxcjsutaej, kgodhobvpq, hfzcgomett, fcjpktywyh, oilleshrxr, bsvmljnmlw, tkednowwvd, cmmdgslysf, xrjeqiitxm, igdftcofgn, nbocxkzutc, gseamqibiy]>
        }
        return true;
    }

    /**
     * Removes a film from the data structure, and any data
     * added through this class related to the film
     * 
     * @param id The film ID
     * @return TRUE if the film has been removed successfully, FALSE otherwise
     */
    @Override
    public boolean remove(int id) {
        if (hashtable.find(id)) {
            hashtable.remove(id);
            movies.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Finds the film IDs of all films released within a given range. If a film is
     * released either on the start or end dates, then that film should not be
     * included
     * 
     * @param start The start point of the range of dates
     * @param end   The end point of the range of dates
     * @return An array of film IDs that were released between start and end
     */
    @Override
    public int[] getAllIDsReleasedInRange(Calendar start, Calendar end) {
        // array to hold films with length of all films
        int[] buffer = new int[movies.size()];
        // var to hold the index to add the movie id to the array
        int j = 0;
        // goes through all the movies
        for (int i=0; i < movies.size(); i++) {
            int id = (int)movies.get(i)[0];
            Calendar release = (Calendar)movies.get(i)[7];
            if (release != null) {
                // checks if the film is in the range
                if (release.compareTo(start) > 0 & release.compareTo(end) < 0) {
                    // adds the movie id to the buffer list
                    buffer[j] = id;
                    j ++;
                }
            }
        }
        // array to hold the movie id's; size of the last index of the buffer array
        int[] ret = new int[j];
        // adds the id from the buffer to the new array
        for (int k=0; k < j; k++) {
            ret[k] = buffer[k];
        }
        return ret;
    }

    /**
     * Finds the film IDs of all films released within a given range and within a
     * given range of budget. If a film is
     * released either on the start or end dates, then that film should not be
     * included. If a film has a budgets exactly the same as the lower or upper
     * bounds, then this film should not be included
     * 
     * @param start       The start point of the range of dates
     * @param end         The end point of the range of dates
     * @param lowerBudget The lowest bound of the range for budgets
     * @param upperBudget The upper bound of the range of budgets
     * @return An array of film IDs that were released between start and end, and
     *         had a budget between lowerBudget and upperBudget
     */
    @Override
    public int[] getAllIDsReleasedInRangeAndBudget(Calendar start, Calendar end, long lowerBudget, long upperBudget) {
        // TODO Build this function
        // gets the movies in the calendar range
        int[] ids = getAllIDsReleasedInRange(start, end);
        // buffer to hold the ids
        int[] buffer = new int[ids.length];
        // var to hold the amount of movies
        int numMovies = 0;
        // finds the movies with the budget in the range
        for (int i=0; i < ids.length; i++) {
            long budget = (long)movies.get(movies.indexOf(ids[i]))[8];
            if (budget > lowerBudget & budget < upperBudget) {
                buffer[i] = ids[i];
                numMovies ++;
            }
        }
        // array to hold the movie id's; size of the last index of the buffer array
        int[] ret = new int[numMovies];
        // adds the id from the buffer to the new array
        for (int k=0; k < numMovies; k++) {
            ret[k] = buffer[k];
        }
        return ret;
    }

    /**
     * Gets the title of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The title of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getTitle(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (String)hashtable.get(id)[1];
        }
        return null;
    }

    /**
     * Gets the original title of a particular film, given the ID number of that
     * film
     * 
     * @param id The movie ID
     * @return The original title of the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public String getOriginalTitle(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (String)hashtable.get(id)[2];
        }
        return null;
    }

    /**
     * Gets the overview of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The overview of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getOverview(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (String)hashtable.get(id)[3];
        }
        return null;
    }

    /**
     * Gets the tagline of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The tagline of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getTagline(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (String)hashtable.get(id)[4];
        }
        return null;
    }

    /**
     * Gets the status of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The status of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getStatus(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (String)hashtable.get(id)[5];
        }
        return null;
    }

    /**
     * Gets the genres of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The genres of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public Genre[] getGenres(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (Genre[])hashtable.get(id)[6];
        }
        return null;
    }

    /**
     * Gets the release date of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The release date of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public Calendar getRelease(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (Calendar)hashtable.get(id)[7];
        }
        return null;
    }

    /**
     * Gets the budget of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The budget of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public long getBudget(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (long)hashtable.get(id)[8];
        }
        return -1;
    }

    /**
     * Gets the revenue of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The revenue of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public long getRevenue(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (long)hashtable.get(id)[9];
        }
        return -1;
    }

    /**
     * Gets the languages of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The languages of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public String[] getLanguages(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (String[])hashtable.get(id)[10];
        }
        return null;
    }

    /**
     * Gets the original language of a particular film, given the ID number of that
     * film
     * 
     * @param id The movie ID
     * @return The original language of the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public String getOriginalLanguage(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (String)hashtable.get(id)[11];
        }
        return null;
    }

    /**
     * Gets the runtime of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The runtime of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public double getRuntime(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (double)hashtable.get(id)[12];
        }
        return -1;
    }

    /**
     * Gets the homepage of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The homepage of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getHomepage(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (String)hashtable.get(id)[13];
        }
        return null;
    }

    /**
     * Gets weather a particular film is classed as "adult", given the ID number of
     * that film
     * 
     * @param id The movie ID
     * @return The "adult" status of the requested film. If the film cannot be
     *         found, then return false
     */
    @Override
    public boolean getAdult(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            return (boolean)hashtable.get(id)[14];
        }
        return false;
    }

    /**
     * Gets weather a particular film is classed as "direct-to-video", given the ID
     * number of that film
     * 
     * @param id The movie ID
     * @return The "direct-to-video" status of the requested film. If the film
     *         cannot be found, then return false
     */
    @Override
    public boolean getVideo(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            // casts from Boolean obj to boolean type
            return (boolean)hashtable.get(id)[15];
        }
        return false;
    }

    /**
     * Gets the poster URL of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The poster URL of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public String getPoster(int id) {
        if (hashtable.find(id)) {
            // casts back from string object to string
            return (String)hashtable.get(id)[16];
        }
        return null;
    }

    /**
     * Sets the average IMDb score and the number of reviews used to generate this
     * score, for a particular film
     * 
     * @param id          The movie ID
     * @param voteAverage The average score on IMDb for the film
     * @param voteCount   The number of reviews on IMDb that were used to generate
     *                    the average score for the film
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean setVote(int id, double voteAverage, int voteCount) {
        // TODO Build this function
        Object[] list = new Object[3];
        Integer addID = id;
        Double addAvg = voteAverage;
        Integer addCount = voteCount;
        list[0] = addID;
        list[1] = addAvg;
        list[2] = addCount;
        if (imdbhashtable.find(id) & hashtable.find(id)) {
            imdbhashtable.set(id, list);
            return true;
        } else if (!(imdbhashtable.find(id)) & hashtable.find(id)) {
            imdbhashtable.add(list);
            return true;
        }
        return false;
    }

    /**
     * Gets the average score for IMDb reviews of a particular film, given the ID
     * number of that film
     * 
     * @param id The movie ID
     * @return The average score for IMDb reviews of the requested film. If the film
     *         cannot be found, then return -1
     */
    @Override
    public double getVoteAverage(int id) {
        // TODO Build this function
        if (imdbhashtable.find(id)) {
            return (double)imdbhashtable.get(id)[1];
        }
        return -1;
    }

    /**
     * Gets the amount of IMDb reviews used to generate the average score of a
     * particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The amount of IMDb reviews used to generate the average score of the
     *         requested film. If the film cannot be found, then return -1
     */
    @Override
    public int getVoteCount(int id) {
        // TODO Build this function
        if (imdbhashtable.find(id)) {
            return (int)imdbhashtable.get(id)[2];
        }
        return -1;
    }

    /**
     * Adds a given film to a collection. The collection is required to have an ID
     * number, a name, and a URL to a poster for the collection
     * 
     * @param filmID                 The movie ID
     * @param collectionID           The collection ID
     * @param collectionName         The name of the collection
     * @param collectionPosterPath   The URL where the poster can
     *                               be found
     * @param collectionBackdropPath The URL where the backdrop can
     *                               be found
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addToCollection(int filmID, int collectionID, String collectionName, String collectionPosterPath,
            String collectionBackdropPath) {
        // TODO Build this function
        Object[] list = new Object[5];
        Integer addfilm = filmID;
        Integer addcol = collectionID;
        String addcolname = collectionName;
        String addcolpos = collectionPosterPath;
        String addcolback = collectionBackdropPath;
        list[0] = addfilm;
        list[1] = addcol;
        list[2] = addcolname;
        list[3] = addcolpos;
        list[4] = addcolback;
        if (hashtable.find(filmID)) {
            if (!(collection.contains((int)list[0]))) {
                collection.add(list);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the name of a given collection
     * 
     * @param collectionID The collection ID
     * @return The name of the collection. If the collection cannot be found, then
     *         return null
     */
    @Override
    public String getCollectionName(int collectionID) {
        // TODO Build this function
        if (collection.contains(collectionID)) {
            int index = collection.indexOf(collectionID);
            return (String)collection.get(index)[2];
        }
        return null;
    }

    /**
     * Gets the poster URL for a given collection
     * 
     * @param collectionID The collection ID
     * @return The poster URL of the collection. If the collection cannot be found,
     *         then return null
     */
    @Override
    public String getCollectionPoster(int collectionID) {
        // TODO Build this function
        if (collection.contains(collectionID)) {
            int index = collection.indexOf(collectionID);
            return (String)collection.get(index)[3];
        }
        return null;
    }

    /**
     * Gets the backdrop URL for a given collection
     * 
     * @param collectionID The collection ID
     * @return The backdrop URL of the collection. If the collection cannot be
     *         found, then return null
     */
    @Override
    public String getCollectionBackdrop(int collectionID) {
        // TODO Build this function
        if (collection.contains(collectionID)) {
            int index = collection.indexOf(collectionID);
            return (String)collection.get(index)[4];
        }
        return null;
    }

    /**
     * Gets the collection ID of a given film
     * 
     * @param filmID The movie ID
     * @return The collection ID for the requested film. If the film cannot be
     *         found, then return -1
     */
    @Override
    public int getCollectionID(int filmID) {
        // TODO Build this function
        if (collection.contains(filmID)) {
            int index = collection.indexOf(filmID);
            return (int)collection.get(index)[1];
        }
        return -1;
    }

    /**
     * Sets the IMDb ID for a given film
     * 
     * @param filmID The movie ID
     * @param imdbID The IMDb ID
     * @return TRUE if the data able to be set, FALSE otherwise
     */
    @Override
    public boolean setIMDB(int filmID, String imdbID) {
        // TODO Build this function
        if (hashtable.find(filmID)) {
            String id = imdbID;
            Object[] item = hashtable.get(filmID);
            item[17] = id;
            hashtable.set(filmID, item);
            return true;
        }
        return false;
    }

    /**
     * Gets the IMDb ID for a given film
     * 
     * @param filmID The movie ID
     * @return The IMDb ID for the requested film. If the film cannot be found,
     *         return null
     */
    @Override
    public String getIMDB(int filmID) {
        // TODO Build this function
        if (hashtable.find(filmID)) {
            return (String)hashtable.get(filmID)[17];
        }
        return null;
    }

    /**
     * Sets the popularity of a given film
     * 
     * @param id         The movie ID
     * @param popularity The popularity of the film
     * @return TRUE if the data able to be set, FALSE otherwise
     */
    @Override
    public boolean setPopularity(int id, double popularity) {
        // TODO Build this function
        if (hashtable.find(id)) {
            Double pop = popularity;
            Object[] item = hashtable.get(id);
            item[18] = pop;
            hashtable.set(id, item);
            return true;
        }
        return false;
    }

    /**
     * Gets the popularity of a given film
     * 
     * @param id The movie ID
     * @return The popularity value of the requested film. If the film cannot be
     *         found, then return -1
     */
    @Override
    public double getPopularity(int id) {
        if (hashtable.find(id)) {
            return (double)hashtable.get(id)[18];
        }
        return -1;
    }

    /**
     * Adds a production company to a given film
     * 
     * @param id      The movie ID
     * @param company A Company object that represents the details on a production
     *                company
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addProductionCompany(int id, Company company) {
        // checks if movie id exists
        if (hashtable.find(id)) {
            // temp var to hold the company
            Company c = company;
            // gets the company array in the comp variable
            Object[] item = hashtable.get(id);
            Company[] comp = (Company[])item[19];
            // checks if the company is already in the array
            boolean found = false;
            for (int j=0; j<comp.length; j++) {
                if (comp[j] == null) {
                    break;
                }
                if (comp[j].getName() == c.getName()) {
                    found = true;
                }
            }
            // if it's not found
            if (!found) {
                // goes through the company array until the first null entry is found and then places the item
                int i = 0;
                boolean placed = false;
                while (!placed & i < comp.length) {
                    if (comp[i] == null) {
                        comp[i] = c;
                        placed = true;
                        break;
                    }
                    i ++;
                }
                item[19] = comp;
                hashtable.set(id, item);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a production country to a given film
     * 
     * @param id      The movie ID
     * @param country A ISO 3166 string containing the 2-character country code
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addProductionCountry(int id, String country) {
        if (hashtable.find(id)) {
            String c = country;
            Object[] item = hashtable.get(id);
            String[] comp = (String[])item[20];
            boolean found = false;
            for (int j=0; j<comp.length; j++) {
                if (comp[j] == null) {
                    break;
                }
                if (comp[j] == c) {
                    found = true;
                }
            }
            if (!found) {
                int i = 0;
                boolean placed = false;
                while (!placed & i < comp.length) {
                    if (comp[i] == null) {
                        comp[i] = c;
                        placed = true;
                        break;
                    }
                    i ++;
                }
                item[20] = comp;
                hashtable.set(id, item);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all the production companies for a given film
     * 
     * @param id The movie ID
     * @return An array of Company objects that represent all the production
     *         companies that worked on the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public Company[] getProductionCompanies(int id) {
        // checks if the movie is exists
        if (hashtable.find(id)) {
            // gets the companies array
            Company[] comp = (Company[])hashtable.get(id)[19];
            int size = 0;
            // works out the size of the array by going through the array until it is null
            for (int i=0; i<comp.length; i++) {
                if (comp[i] == null) {
                    break;
                } else {
                    size ++;
                }
            }
            // buffer return object
            Company[] ret = new Company[size];
            int j = 0;
            // adds the company to the buffer, excludes the null values because of the size variable
            while (j < size) {
                ret[j] = new Company(comp[j].getID(), comp[j].getName());
                j++;
            }
            return ret;
        }
        return null;
    }

    /**
     * Gets all the production companies for a given film
     * 
     * @param id The movie ID
     * @return An array of Strings that represent all the production countries (in
     *         ISO 3166 format) that worked on the requested film. If the film
     *         cannot be found, then return null
     */
    @Override
    public String[] getProductionCountries(int id) {
        // TODO Build this function
        if (hashtable.find(id)) {
            String[] comp = (String[])hashtable.get(id)[20];
            int size = 0;
            for (int i=0; i<comp.length; i++) {
                if (comp[i] == null) {
                    break;
                } else {
                    size ++;
                }
            }
            String[] ret = new String[size];
            int j = 0;
            while (j < size) {
                ret[j] = comp[j];
                j ++;
            }
            return ret;
        }
        return null;
    }

    /**
     * States the number of movies stored in the data structure
     * 
     * @return The number of movies stored in the data structure
     */
    @Override
    public int size() {
        return hashtable.size();
    }

    /**
     * Produces a list of movie IDs that have the search term in their title,
     * original title or their overview
     * 
     * @param searchTerm The term that needs to be checked
     * @return An array of movie IDs that have the search term in their title,
     *         original title or their overview. If no movies have this search term,
     *         then an empty array should be returned
     */
    @Override
    public int[] findFilms(String searchTerm) {
        // array to hold films with length of all films
        int[] buffer = new int[movies.size()];
        // var to hold the index to add the movie id to the array
        int j = 0;
        // goes through all the movies
        for (int i=0; i < movies.size(); i++) {
            int id = (int)movies.get(i)[0];
            String title = (String)movies.get(i)[1];
            String originalTitle = (String)movies.get(i)[2];
            String desc = (String)movies.get(i)[3];
            // checks if the term is present
            if (title.contains(searchTerm) | originalTitle.contains(searchTerm) | desc.contains(searchTerm)) {
                // adds the movie id to the buffer list
                buffer[j] = id;
                j ++;
            }
        }
        // array to hold the movie id's; size of the last index of the buffer array
        int[] ret = new int[j];
        // adds the id from the buffer to the new array
        for (int k=0; k < j; k++) {
            ret[k] = buffer[k];
        }
        return ret;
    }
}
