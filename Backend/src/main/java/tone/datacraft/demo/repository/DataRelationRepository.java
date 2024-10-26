package tone.datacraft.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tone.datacraft.demo.document.DataRelationDocument;

@Repository
public interface DataRelationRepository extends MongoRepository<DataRelationDocument, String> {
}
