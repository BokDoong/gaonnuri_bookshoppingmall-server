package GaonNuri.Project.ShoppingMall.item.service;

import GaonNuri.Project.ShoppingMall.item.repository.ItemsRepository;
import GaonNuri.Project.ShoppingMall.item.service.inter.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.DetailItemsInfo;
import static GaonNuri.Project.ShoppingMall.item.data.dto.ItemsResponseDto.ItemsInfo;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository itemsRepository;

    /**
     * 상품 목록 조회 - 상세내용 제외
     * @param page : 페이지
     * @param size : 페이지당 나올 item 수
     */
    @Override
    public Page<ItemsInfo> showItemsOnly(int page, int size) {

        List<ItemsInfo> itemsInfos = itemsRepository.findAll().stream().map(ItemsInfo::entityToDTO)
                .collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, size);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), itemsInfos.size());

        Page<ItemsInfo> itemsInfoPage = new PageImpl<>(itemsInfos.subList(start, end), pageRequest, itemsInfos.size());

        return itemsInfoPage;
    }

    /**
     * 상품 목록 조회 - 상세내용 포함
     * @param page : 페이지
     * @param size : 페이지당 나올 item 수
     */
    @Override
    public Page<DetailItemsInfo> showItemsDetails(int page, int size) {

        List<DetailItemsInfo> detailItemsInfos = itemsRepository.findAll().stream().map(DetailItemsInfo::entityToDTO)
                .collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(page, size);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start) + pageRequest.getPageSize(), detailItemsInfos.size());

        Page<DetailItemsInfo> detailItemsInfoPage = new PageImpl<>(detailItemsInfos.subList(start, end), pageRequest, detailItemsInfos.size());

        return detailItemsInfoPage;
    }

    /**
     * 상품 단건 조회
     * @param id : 상품 Id
     */
    @Override
    public DetailItemsInfo ItemsDetails(long id) {

        DetailItemsInfo detailItemsInfo = new DetailItemsInfo();

        return detailItemsInfo.entityToDTO(itemsRepository.getItemsById(id));
    }
}