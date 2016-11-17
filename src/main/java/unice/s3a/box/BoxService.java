package unice.s3a.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Box service.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BoxService {
    @Autowired private BoxRepository busRepository;

    /**
     * Delete box.
     * @param bus the bus
     * @return the box
     */
    @Transactional
    public Box delete(Box bus) {
        busRepository.delete(bus);
        return bus;
    }

    /**
     * Save box.
     * @param bus the bus
     * @return the box
     */
    @Transactional
    public Box save(Box bus) {
        busRepository.save(bus);
        return bus;
    }
}
