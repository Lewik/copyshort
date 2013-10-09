import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class SystemClipboardDataManipulate {

    /**
     * When you do a cut or copy of text in the operating system, the text is
     * stored in the clipboard.
     *
     * The following method returns the content that is currently in the
     * clipboard.
     *
     */
    public static String getClipboardData() {
        String clipboardText;
        Transferable trans = Toolkit.getDefaultToolkit().getSystemClipboard()
                .getContents(null);

        try {
            if (trans != null  && trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                clipboardText = (String) trans
                        .getTransferData(DataFlavor.stringFlavor);
                return clipboardText;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method will set any parameter string to the system's clipboard.
     */
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
                stringSelection, null);
    }

    /**
     * Testing the clipboard set and get methods.
     */
    public static void main(String[] args) {

        /**
         * If something is already there in the clipboard, printing the value.
         */
        System.out.println(getClipboardData());

        /**
         * Setting our own clipboard data
         */
        setClipboardData("Sanjaal.com/java");

        /**
         * Printing the Clipboard Data We Just Set.
         */
        System.out.println(getClipboardData());

    }

}