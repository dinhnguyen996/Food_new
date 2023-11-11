package myapp.singleton

class Singleton {
    companion object {
        var ds = ArrayList<ModelDetailSingletonMycard>()
        fun additems(item : List<ModelDetailSingletonMycard>){
            ds.addAll(item)
        }
        fun additem(item: ModelDetailSingletonMycard){
            ds.add(item)
        }
        fun getitem(): ArrayList<ModelDetailSingletonMycard>{
            return ds
        }
    }

}