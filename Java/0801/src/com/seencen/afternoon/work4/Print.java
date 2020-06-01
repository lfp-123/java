package com.seencen.afternoon.work4;

/**
 * @author asus
 * @Title: Print
 * @ProjectName Java
 * @Description: TODO
 * @date 2019/8/1 15:19
 */
public class Print {
    private Inkbox inkbox;
    private Paper paper;

    public Print(Inkbox inkbox,Paper paper){
        setInkbox(inkbox);
        setPaper(paper);
    }
    public  void show(String content){

        System.out.println("打印机正在使用"+inkbox.color()+"打印机在"
                +paper.paperSize()+"纸张上打印"+content);
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Inkbox getInkbox() {
        return inkbox;
    }

    public void setInkbox(Inkbox inkbox) {
        this.inkbox = inkbox;
    }
}
