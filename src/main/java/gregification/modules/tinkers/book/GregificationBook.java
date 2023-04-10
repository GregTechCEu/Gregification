package gregification.modules.tinkers.book;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.TinkerBook;

@SideOnly(Side.CLIENT)
public class GregificationBook extends BookData {

    public static void register() {
        TinkerBook.INSTANCE.addRepository(new FileRepository("gregification:book"));
        TinkerBook.INSTANCE.addTransformer(new BookAdditions());
    }
}
