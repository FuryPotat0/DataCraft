package tone.datacraft.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tone.datacraft.demo.document.DataTypeDocument;

@Repository
public interface DataTypeRepository extends MongoRepository<DataTypeDocument, Long> {

}

