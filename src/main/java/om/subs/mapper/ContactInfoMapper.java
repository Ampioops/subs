package om.subs.mapper;

import om.subs.entity.ContactInfo;
import om.subs.model.request.ContactInfoRequest;
import om.subs.model.response.ContactInfoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {

    ContactInfoResponse toResponse(ContactInfo contactInfo);
    ContactInfo toContactInfo(ContactInfoRequest contactInfoRequest);

}
