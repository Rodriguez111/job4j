package sellcars.persistent;

import sellcars.models.Photo;

public interface PhotoStorage {

    String add(Photo photo);

    String delete(Photo photo);

    Photo findPhotoByAdvertId(int id);


}
