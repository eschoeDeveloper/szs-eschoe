package com.szs.restapi.domain.user.whitelist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWhiteListServiceImpl implements UserWhiteListService {

    private final UserWhiteListRepository userWhiteListRepository;

    @Override
    public boolean checkUserWhiteList(UserWhiteListDTO userWhiteListDTO) throws RuntimeException {

        List<UserWhiteListInterface> userWhiteList = userWhiteListRepository.checkUserWhiteList(userWhiteListDTO);
        return !ObjectUtils.isEmpty(userWhiteList) && !CollectionUtils.isEmpty(userWhiteList);

    }

}
