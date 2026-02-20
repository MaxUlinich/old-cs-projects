public class AnimateOnce {
    
    private Effect e;
    private int end;
    private boolean needToDraw;
    public AnimateOnce(Effect e, int end, boolean needToDraw){
        this.e = e;
        this.end = end;
        this.needToDraw = needToDraw;
    }
    public Effect getE(){
        return e;
    }
    public int getEnd(){
        return end;
    }
    public boolean needToDraw(){
        return needToDraw;
    }

}

