package hillsboro.philoarte.scalar.repositories;

import hillsboro.philoarte.scalar.entities.ArtFile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtFileDao extends JpaRepository<ArtFile, Long> {

    @EntityGraph(attributePaths = {"art"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT f FROM ArtFile f WHERE f.art.artId = :artId")
    List<ArtFile> getFilesByArtId(@Param("artId") Long artId);

    @Modifying
    @Query("DELETE FROM ArtFile f WHERE f.art.artId = :artId")
    void deleteByArtId(@Param("artId") Long artId);

    @Query("SELECT f.fileId FROM ArtFile f WHERE f.uuid = :uuid")
    Long findByUuid(@Param("uuid") String uuid);

}
