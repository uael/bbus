package unice.s3a.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Transactional
    public Bus save(Bus bus) {
        busRepository.save(bus);
        return bus;
    }
}
