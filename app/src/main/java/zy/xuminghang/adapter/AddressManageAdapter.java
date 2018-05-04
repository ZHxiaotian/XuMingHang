package zy.xuminghang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import zy.xuminghang.R;
import zy.xuminghang.activity.AddressManageActivity;
import zy.xuminghang.activity.UpdateAddressActivity;
import zy.xuminghang.entity.AddressManageBean;
import zy.xuminghang.util.AddressDao;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
public class AddressManageAdapter extends BaseAdapter {
    List<AddressManageBean> list;
    Context context;
    private AddressDao dao;
    public AddressManageAdapter(List<AddressManageBean> list, Context context) {
        this.list = list;
        this.context = context;
        dao=new AddressDao(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final AddressManageBean bean=list.get(position);
        ViewHolder holder=null;
        if(convertView==null) {

            convertView = View.inflate(context, R.layout.item_addmanager, null);
            holder=new ViewHolder();
            holder.ckb_item_addmanage_defult= (CheckBox) convertView.findViewById(R.id.ckb_item_addmanage_defult);
            holder.setdefault= (CheckBox) convertView.findViewById(R.id.ck_chose);
            holder.name= (TextView) convertView.findViewById(R.id.et_addmanager_name);
            holder.phoneno= (TextView) convertView.findViewById(R.id.tv_addmanager_phonenumer);
            holder.address= (TextView) convertView.findViewById(R.id.tv_item_address);
            holder.update= (TextView) convertView.findViewById(R.id.tv_addmanager_maj);
            holder.delete= (TextView) convertView.findViewById(R.id.tv_item_delete);
            holder.deletesingleaddress= (LinearLayout) convertView.findViewById(R.id.ll_addressmanagedelete);
            holder.updateAddress= (LinearLayout) convertView.findViewById(R.id.ll_addressmanedit);
            holder.setdefaultadd= (LinearLayout) convertView.findViewById(R.id.ll_addressmanage_setdefault);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }


        holder.name.setText(bean.getUsername());
        holder.phoneno.setText(bean.getUsetphoneno());
        holder.address.setText(bean.getDescaddress());
        if(bean.isdefault()==1){

            holder.ckb_item_addmanage_defult.setChecked(true);
        }else{
            holder.ckb_item_addmanage_defult.setChecked(false);
        }
        holder.setdefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      if(isChecked){
                          bean.setIsdefault(1);
                          for( AddressManageBean data:list){
                              if(bean.getUsercode()==data.getUsercode()){
                                  continue;
                              }else{
                                  data.setIsdefault(0);
                                  dao.updateMsg(data);
                              }
                          }
                          int flag=   dao.updateMsg(bean);
                                if(flag!=0){

                                    list.set(position,bean);
                                    AddressManageAdapter.this.notifyDataSetChanged();
                                }
                      }
            }
        });
         holder.deletesingleaddress.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                int i= dao.deleteAddress(bean.getId()+"");
                 if(i!=0){
                     list.remove(position);
                     AddressManageAdapter.this.notifyDataSetChanged();
                 }
             }
         });
        holder.updateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UpdateAddressActivity.class);
                intent.putExtra("addressbean",bean);
                intent.putExtra("position",position);

                ((AddressManageActivity) context).startActivityForResult(intent,0x02);
            }
        });
        holder.setdefaultadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setIsdefault(1);
                for( AddressManageBean data:list){
                    if(bean.getUsercode()==data.getUsercode()){
                        continue;
                    }else{
                        data.setIsdefault(0);
                        dao.updateMsg(data);
                    }
                }
                int flag=   dao.updateMsg(bean);
                if(flag!=0){

                    list.set(position,bean);
                    AddressManageAdapter.this.notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }


    class ViewHolder{

          private CheckBox ckb_item_addmanage_defult;
          private TextView name;//
          private TextView phoneno;
        private TextView  address;
        private CheckBox setdefault;
        private TextView update;
        private TextView delete;
        private LinearLayout deletesingleaddress;
        private LinearLayout updateAddress;
        private LinearLayout setdefaultadd;

    }




}
