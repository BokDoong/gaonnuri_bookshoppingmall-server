package GaonNuri.Project.ShoppingMall.item.data.entity;

import GaonNuri.Project.ShoppingMall.config.entity.BaseTimeEntity;
import GaonNuri.Project.ShoppingMall.item.data.enums.ItemStatus;
import lombok.*;

import javax.persistence.*;

import static GaonNuri.Project.ShoppingMall.admin.dto.ItemsRequestDto.ItemsUpdateInfo;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Items extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "item_id")
    private long id;

    @Column(name= "item_name", nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    //재고
    @Column(nullable = false)
    private int stockNumber;

    //판매상태
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    //상품 수정 - 관리자
    public void updateItem(ItemsUpdateInfo dto){
        if(dto.getItemName()!=null)
            this.itemName = dto.getItemName();
        if(dto.getPrice()>=0)
            this.price = dto.getPrice();
        if(dto.getItemDetail()!=null)
            this.itemDetail = dto.getItemDetail();
        if(dto.getItemStatus()!=null)
            this.itemStatus = dto.getItemStatus();
    }

    //재고 증가
//    public void addStock(int cancelStock) {
//        if(this.itemStatus == ItemStatus.SOLD_OUT) {
//            this.itemStatus = ItemStatus.FOR_SALE;
//        }
//
//        this.stockNumber += cancelStock;
//    }
}
