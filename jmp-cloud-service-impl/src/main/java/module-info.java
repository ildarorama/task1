import com.epam.jmp.service.Service;
import com.epam.jmp.service.impl.ServiceImpl;


module jmp.cloud.service.impl {
    requires transitive jmp.service.api;
    requires jmp.dto;
    provides Service with ServiceImpl;
}
