package org.d3ifcool.superuser.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.presenters.KategoriJudulPresenter;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KoorJudulPaKategoriViewAdapter extends RecyclerView.Adapter<KoorJudulPaKategoriViewAdapter.ViewHolder> {

    private ArrayList<KategoriJudul> data;
    private Context context;
    private int layoutType;

    private AlertDialog.Builder mDialog;
    private View mDialogView;

    private KategoriJudulPresenter presenter;

    public KoorJudulPaKategoriViewAdapter(Context context, KategoriJudulPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }


    public void setKategoriJudul(ArrayList<KategoriJudul> kategori) {
        this.data = kategori;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_kategori;
        ImageView btn_hapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_kategori = itemView.findViewById(R.id.ctn_koor_judul_kategori);
            btn_hapus = itemView.findViewById(R.id.ctn_koor_button_hapus);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tv_kategori.setText(data.get(position).getKategori_nama());

        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog
                    .Builder(context)
                    .setTitle(context.getString(org.d3ifcool.dosen.R.string.dialog_hapus_title))
                    .setMessage(context.getString(org.d3ifcool.dosen.R.string.dialog_hapus_text))
                    .setPositiveButton(org.d3ifcool.dosen.R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.deleteKategori(data.get(position).getId());
                            notifyDataSetChanged();
                            presenter.getKategori();
                            dialog.dismiss(); // Keluar Dari Dialog
                            if (mDialogView.getParent() != null) {
                                ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                            }
                        }
                    })

                    .setNegativeButton(org.d3ifcool.dosen.R.string.tidak, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // Keluar Dari Dialog
                            if (mDialogView.getParent() != null) {
                                ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                            }
                        }
                    })

                    .show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogView = LayoutInflater.from(context).inflate(R.layout.content_item_dialog_edit_kategori, null);
                mDialog = new AlertDialog.Builder(mDialogView.getContext());
                mDialog.setView(mDialogView);
                mDialog.setCancelable(true);
                mDialog.setPositiveButton(context.getText(R.string.ubah), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv_title = mDialogView.findViewById(R.id.ctn_dialog_title);
                        tv_title.setText("Ubah Kategori Judul");
                        EditText et_kategori = mDialogView.findViewById(R.id.dialog_kategori_ubah);
                        String result = et_kategori.getText().toString();
                        presenter.updateKategori(data.get(position).getId() , result);
                        notifyDataSetChanged();
                        presenter.getKategori();
                        dialog.dismiss(); // Keluar Dari Dialog
                        if (mDialogView.getParent() != null) {
                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                        }
                    }
                });

                mDialog.setNegativeButton(context.getText(R.string.batal), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Keluar Dari Dialog
                        if (mDialogView.getParent() != null) {
                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                        }
                    }
                });
                mDialog.show();
            }
        });

    }

}
